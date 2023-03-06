<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//data
	String name = null;
	String login_id = null;
	
	//cookie
	/*
	Cookie[] ar = request.getCookies();//특정 쿠키를 얻을수가 없으므로 모든 쿠키를 다 가져온다.
	if(ar != null) {
		for(int i=0; i<ar.length; i++){
			String cookieName = ar[i].getName();	
			String cookieValue = ar[i].getValue();	
			
			System.out.println("쿠키명 = " + cookieName);
			System.out.println("쿠키값 = " + cookieValue);
			System.out.println();
			
			if(cookieName.equals("memName")) name = cookieValue;
			if(cookieName.equals("memId")) login_id = cookieValue;
		}//for
		
	}//if
	*/
	
	
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
<h3>로그인 성공</h3>
		<p><%=name%>님이 로그인 하였습니다</p>
		<input type="button" value="회원정보 수정" onclick="location.href='updateForm.jsp'">
		<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
		<input type="button" value="회원탈퇴" onclick="location.href='deleteForm.jsp'">



</body>
</html>