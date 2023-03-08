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
		MemberDTO memberDTO = memberDAO.memberLogin(login_id, login_pw);
		
		
		//쿠키
				
				
	
		
	if (memberDTO == null ) {
		//페이지 이동
		response.sendRedirect("loginFail.jsp");

	} else {
		//세션생성
		session.setAttribute("memName", memberDTO.getName());
		session.setAttribute("memId", login_id);
		session.setAttribute("memPwd", login_pw);
		session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
		
		//페이지 이동
		response.sendRedirect("loginOk.jsp");

	}
%>
</body>
</html>








