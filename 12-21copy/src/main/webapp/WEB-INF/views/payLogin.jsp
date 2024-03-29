<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Login Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            text-align: center;
            border: 1px solid #ccc;
            padding: 20px;
            width: 300px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        input {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="checkId" method="post" onsubmit="return validateForm()">
            <div>
                <label for="id">아이디:</label>
                <input type="text" id="id" name="id" required>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="pwd" name="pwd" required>
            </div>
            <div>
                <input type="hidden" id="error" value="<%= request.getAttribute("error") %>">
                <button type="submit">로그인</button>
            </div>
        </form>
    </div>

    <script>
        function validateForm() {
            var error = document.getElementById("error").value;

            if (error !== null && error !== "null" && error !== "") {
                alert(error);
                location.reload(); // 에러가 있을 경우 페이지 리로딩
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
