<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 내용</title>

    <!-- 추가된 CSS 스타일 -->
    <style>
        .table-container {
            margin-top: 20px;
        }
        .back-button,
        .edit-button,
        .delete-button {
            margin-top: 20px;
        }
        .content-container {
            margin-top: 20px;
        }
        .content-cell {
            vertical-align: top; /* 셀의 내용을 상단에 정렬 */
        }
    </style>
</head>
<body>
    <h1>글 내용</h1>
    <c:choose>
        <c:when test="${not empty post}">
            <!-- 변경된 테이블 구조 -->
            <table border="1" class="table-container">
                <tr>
                    <th>글 번호</th>
                    <th>작성자 (id)</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>수정일</th>
                    <th>조회수</th>
                </tr>
                <tr>
                    <td>${post.seq}</td>
                    <td>${post.mem_id}</td>
                    <td>${post.board_subject}</td>
                    <td>${post.reg_date}</td>
                    <td>${post.upt_date}</td>
                    <td>${post.view_cnt}</td>
            </table>
            
            <!-- 내용 표시 및 수정 -->
            <div class="content-container">
                <h2>내용</h2>
                <form action="writeOrUpdatePost" method="post">
                    <input type="hidden" name="seq" value="${post.seq}">
                    <input type="hidden" name="memName" value="${post.mem_name}">
                    <input type="hidden" name="memId" value="${post.mem_id}">
                    <input type="hidden" name="boardSubject" value="${post.board_subject}">
                    <!-- 기타 필요한 히든 필드 추가 -->

                    <!-- 수정할 내용 입력 필드 -->
                    <textarea name="boardContent" rows="5" cols="50">${post.board_content}</textarea>

                    <input type="submit" value="수정 완료">
                </form>

			<!-- 파일 목록에 접근하여 이미지 표시 및 다운로드 링크 -->
			<c:forEach var="file" items="${files}">
			    <div>
			        <!-- 이미지 표시 -->
			        <div>
			        	파일 이름 : 
					  ${file.realName},
					  
					  저장된 이름 : ${file.saveName },
			        </div>
			
			        <img src="${pageContext.request.contextPath}/resources/upload/${file.saveName}" alt="Image">
			
			        <!-- 이미지 다운로드 링크 -->
			        <a href="${pageContext.request.contextPath}/downloadImage?fileName=${file.saveName}" download>다운로드</a>
			    </div>
			</c:forEach>
							




                <!-- 삭제 버튼 -->
                <form action="deletePost" method="post" class="delete-button">
                    <input type="hidden" name="seq" value="${post.seq}">
                    <input type="submit" value="삭제">
                </form>
            </div>
            
            <!-- 뒤로가기 버튼 -->
            <form action="goToList" method="get" class="back-button">
                <input type="submit" value="뒤로가기">
            </form>
        </c:when>
        <c:otherwise>
            <!-- 데이터가 없을 경우 -->
            <p>해당 글이 없습니다.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
