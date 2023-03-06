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
		
		
		//쿠키
				
				
	
		
	if (name == null ) {
		//페이지 이동
		response.sendRedirect("loginFail.jsp");

	} else {
		//쿠키
		/*
		Cookie cookie = new Cookie("memName", name);
		cookie.setMaxAge(30 * 60); //초 단위  -30분
		response.addCookie(cookie);//클라이언트 보내기
		
		Cookie cookie2 = new Cookie("memId", login_id);
		cookie2.setMaxAge(30 * 60); //초 단위
		response.addCookie(cookie2);//클라이언트 보내기
		*/
		//세션
		//HttpSession session = request.getSession(); - jsp는 세션이 내장객체에 이미 들어가 있다
		
		session.setMaxInactiveInterval(30*60);
		session.setAttribute("memName", name);
		session.setAttribute("memId", login_id);
		System.out.println("이름 = " + session.getAttribute("memName"));
		
		
		//페이지 이동
		response.sendRedirect("loginOk.jsp");

	}
%>
</body>
</html>








