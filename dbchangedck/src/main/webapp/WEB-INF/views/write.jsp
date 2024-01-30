<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
</head>
<body>
    <h1>글쓰기</h1>
    <form action="writeOrUpdatePost" method="post">
        <label for="memName">작성자:</label>
        <input type="text" name="memName" id="memName"><br>

        <label for="memId">ID:</label>
        <input type="text" name="memId" id="memId"><br>

        <label for="boardSubject">제목:</label>
        <input type="text" name="boardSubject" id="boardSubject"><br>

        <label for="boardContent">내용:</label>
        <textarea name="boardContent" id="boardContent"></textarea><br>

        <input type="submit" value="글쓰기">
    </form>
</body>
</html>
