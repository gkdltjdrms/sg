<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
    <h1>Welcome to Home</h1>
    
    <!-- 글쓰기 버튼 -->
    <form action="goToWrite" method="get">
        <input type="submit" value="글쓰기">
    </form>

    <!-- 글목록 버튼 -->
    <form action="goToList" method="get">
        <input type="submit" value="글목록">
    </form>
</body>
</html>
