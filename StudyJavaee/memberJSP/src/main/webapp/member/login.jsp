<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO"%>
<%@ page import="member.been.MemberDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%
		// 데이터
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
	
	// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		String name = memberDAO.memberLogin(login_id, login_pw);
	if (name == null ) {
%>
		<h3>로그인 실패</h3>
		<p>아이디 혹은 비밀번호가 맞지 않습니다</p>
		<button onclick="history.back()">뒤로</button>
<%
	} else {
%>
		<h3>로그인 성공</h3>
		<p><%=name%>님이 로그인 하였습니다</p>
		<input type="button" value="회원정보 수정" onclick="location.href='http://localhost:8080/memberJSP/member/updateForm.jsp?login_id=<%=login_id%>';">
		
		
<%
	}
%>
</body>
</html>