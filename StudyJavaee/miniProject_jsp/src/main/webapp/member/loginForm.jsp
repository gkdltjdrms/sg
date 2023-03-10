<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  <style>
  body {
    font-family: Arial, sans-serif;
    background-color: #fff;
  }
  h1 {
    text-align: center;
    margin-top: 50px;
    margin-bottom: 30px;
  }
  form {
    background-color: #f2f2f2;
    width: 400px;
    margin: 0 auto;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
  }
  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }
  input[type="text"],
  input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-bottom: 15px;
    box-sizing: border-box;
    font-size: 16px;
  }
  input[type="submit"] {
    background-color: #4CAF50;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
  }
  input[type="submit"]:hover {
    background-color: #3e8e41;
  }
  .error {
    color: red;
    font-weight: bold;
    margin-top: 5px;
    margin-bottom: 10px;
  }
  img {
    margin: 0 auto;
    display: block;
  }
</style>
</head>
<body>
  <img src="../image/image2.png" width="120" height="100" alt="망상토끼"
				onclick="location.href='../index.jsp'" style="cursor: pointer">
  <h1>로그인</h1>
  <form name="loginform" method="post" action="http://localhost:8080/miniProject_jsp/member/login.jsp">
    <label for="login_id">아이디:</label>
    <input type="text" id="login_id" name="login_id" placeholder="아이디 입력">
    <div class="error" id="id_check"></div>
    <label for="login_pw">비밀번호:</label>
    <input type="password" id="login_pw" name="login_pw" placeholder="비밀번호 입력">
    <div class="error" id="pw_check"></div>
    <input type="submit" value="로그인" onclick="check_login()">
  </form>
  <script> 
    const form = document.loginform;
    function check_login() {
      document.getElementById("id_check").innerText = ""
      document.getElementById("pw_check").innerText = ""
      if (form.login_id.value == "") {
        document.getElementById("id_check").innerText = "아이디를 입력하세요."
      }
      else if (form.login_pw.value == "") {
        document.getElementById("pw_check").innerText = "비밀번호를 입력하세요."
      }
      else {
        form.submit();
      }
    }
  </script> 
</body>
</html>
