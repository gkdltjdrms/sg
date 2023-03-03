
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>로그인</title>
	<style>
		.error-message {
			font-size: 10px;
			color: red;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<h1>로그인</h1>
	<br>
	<form name="login-form" method="post" action="login.jsp">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>로그인</th>
				<td>
					<input type="text" id="login_id" name="login_id" placeholder="아이디 입력">
					<div id="id_check" class="error-message"></div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="login_pw" name="login_pw" placeholder="비밀번호 입력">
					<div id="pw_check" class="error-message"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="로그인" onClick="check_login()"> 
					<input type="button" value="회원가입" onClick="location.href='http://localhost:8080/memberJSP/member/WriteForm.jsp'">
				</td>
			</tr>
		</table>
	</form>
	<script src="../js/member.js"></script> 
</body>
</html>
