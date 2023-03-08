<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//data
	String name = null;
	String login_id = null;
	
	
	
	
	name = (String)session.getAttribute("memName");
	System.out.println(name);
	login_id = (String)session.getAttribute("memId");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="../image/image2.png" width="120" height="100" alt="망상토끼"
 onclick="location.href='index.jsp'" style="cursor: pointer">

		<h3><%=name%>님이 로그인 하였습니다</h3>
		<input type="button" value="홈" onclick="location.href='index.jsp'">
		
		<input type="button" value="회원정보 수정" onclick="location.href='updateForm.jsp'">
		<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
		<input type="button" value="회원탈퇴" onclick="location.href='deleteForm.jsp'">
		<input type="button" value="로그인" onclick="location.href='http://localhost:8080/memberJSP/member/loginForm.jsp';">


</body>
</html>