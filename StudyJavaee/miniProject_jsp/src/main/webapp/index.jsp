<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }
    header {
        background-color: #333;
        color: white;
        padding: 20px;
        text-align: center;
    }
    nav {
        background-color: #555;
        overflow: hidden;
    }
    nav a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-weight: bold;
    }
    nav a:hover {
        background-color: #f2f2f2;
        color: #555;
    }
    section {
        margin: 20px;
        padding: 20px;
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
</style>
</head>
<body>
<header>
    <h1>*** 메인화면 ***</h1>
</header>
<nav>
	<% if(session.getAttribute("memId") == null) {%>
    <a href="member/WriteForm.jsp">회원가입</a>
    <a href="member/loginForm.jsp">로그인</a>
    
    <%}else{%> 	
    <a href="member/logout.jsp">로그아웃</a>
    <a href="member/updateForm.jsp">회원정보수정</a>
    <a href="member/deleteForm.jsp">회원탈퇴</a>
    <a href="board/boardWriteForm.jsp">글쓰기</a>
    
    <%}%>
    <a href="board/boardList.jsp?pg=1">목록</a>
</nav>
<section>
    <h2>메인화면</h2>
    <p>이곳은 메인화면입니다.</p>
</section>
</body>
</html>
