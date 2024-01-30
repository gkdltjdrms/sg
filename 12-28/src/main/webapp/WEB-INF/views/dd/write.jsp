<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        .file-container {
            margin-top: 10px;
        }

        .file-item {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
        }

        .file-item img {
            max-width: 100px;
            max-height: 100px;
            margin-right: 10px;
        }

        .delete-btn {
            cursor: pointer;
            color: red;
            margin-left: 10px;
        }
    </style>
    <title>글쓰기</title>
</head>
<body>
    <h1>글쓰기</h1>
    <form action="writeOrUpdatePost" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <label for="memName">작성자:</label>
        <input type="text" name="memName" id="memName"><br>

        <label for="memId">ID:</label>
        <input type="text" name="memId" id="memId"><br>

        <label for="boardSubject">제목:</label>
        <input type="text" name="boardSubject" id="boardSubject"><br>

        <label for="boardContent">내용:</label>
        <textarea name="boardContent" id="boardContent"></textarea><br>

        <!-- 파일 업로드를 위한 input 요소 추가 -->
        <div class="file-container">
            <button type="button" id="addFile">이미지 추가</button>
        </div>

        <div id="fileList"></div>

        <input type="submit" value="글쓰기">
    </form>

    <script>
    function validateForm() {
        var fileInputs = document.getElementsByName("files");
        var isValid = true;

        function processFile(file, callback) {
            var img = new Image();
            var _URL = window.URL || window.webkitURL;

            img.onload = function () {
                var maxWidth = 100;
                var maxHeight = 100;

                if (img.width > maxWidth || img.height > maxHeight) {
                    alert("이미지는 최대 가로 " + maxWidth + "px, 세로 " + maxHeight + "px을 초과할 수 없습니다.");
                    document.getElementById("fileList").innerHTML = ""; // 이미지 업로드 취소 시 파일 목록 초기화
                    callback(false); // 업로드 취소
                } else {
                    callback(true); // 이미지 크기가 유효하면 계속 진행
                }
            };

            img.src = _URL.createObjectURL(file);
        }

        function processFileInput(fileInput, callback) {
            var file = fileInput.files[0];

            if (!file) {
                callback(true); // 파일이 선택되지 않은 경우 계속 진행
                return;
            }

            var fileName = file.name;
            var ext = fileName.slice((fileName.lastIndexOf(".") - 1 >>> 0) + 2);

            if (!isImageExtension(ext)) {
                alert("이미지 파일만 업로드 가능합니다.");
                callback(false); // 업로드 취소
                return;
            }

            processFile(file, callback);
        }

        var remainingFiles = fileInputs.length;

        function checkRemainingFiles() {
            remainingFiles--;

            if (remainingFiles === 0) {
                // 모든 파일을 확인했을 때 폼 제출 여부 결정
                return isValid;
            }
        }

        for (var i = 0; i < fileInputs.length; i++) {
            processFileInput(fileInputs[i], function (isValidFile) {
                isValid = isValid && isValidFile;
                if (checkRemainingFiles() !== undefined) {
                    // 모든 파일을 확인했을 때 폼 제출 여부 결정
                    if (isValid) {
                        document.forms[0].submit();
                    }
                }
            });
        }

        return false; // 폼 제출을 여기서 막음
    }


        function isImageExtension(ext) {
            var imageExtensions = ["jpg", "jpeg", "png", "gif"];
            return imageExtensions.includes(ext.toLowerCase());
        }

        document.addEventListener("DOMContentLoaded", function () {
            // 추가 버튼 클릭 시 파일 업로드 input 추가
            document.getElementById("addFile").addEventListener("click", function () {
                var fileList = document.getElementById("fileList");
                var fileItem = document.createElement("div");
                fileItem.className = "file-item";

                var fileInput = document.createElement("input");
                fileInput.type = "file";
                fileInput.name = "files";
                fileInput.accept = "image/*"; // 이미지 파일만 허용
                fileItem.appendChild(fileInput);

                var deleteBtn = document.createElement("span");
                deleteBtn.className = "delete-btn";
                deleteBtn.innerText = "삭제";
                deleteBtn.addEventListener("click", function () {
                    fileItem.remove();
                });
                fileItem.appendChild(deleteBtn);

                fileList.appendChild(fileItem);
            });
        });
    </script>
</body>
</html>
