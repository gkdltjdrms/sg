<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>선택 삭제</title>
   
 
 
 
</head>
<body>
    <h1>글 삭제</h1>   
    <c:if test="${param.message == 'NoItemsSelected'}">
        <p style="color: red;">한 개 이상의 항목을 선택하세요.</p>
    </c:if>
    
    <!-- 글쓰기 버튼 -->
    <form action="goToWrite" method="get">
        <input type="submit" value="글쓰기">
    </form>

    <form action="deleteSelectedPosts" method="post">
        <input type="submit" value="선택 삭제" onclick="toggleCheckboxes()">
        <table border="1">
            <tr>
                <th>글 번호</th>
                <th>작성자 (id)</th>
                <th>제목</th>
                <th>작성일</th>
                <th>수정일</th>
                <th>조회수</th>
                <th class="checkbox-column">선택</th>
            </tr>
            <c:forEach items="${listOfPosts}" var="post">
                <tr>
                    <td>${post.seq}</td>
                    <td>${post.mem_id}</td>
                    <td>
                        <!-- 글 제목에 링크 추가 -->
                        <a href="listinfo?seq=${post.seq}">${post.board_subject}</a>
                    </td>
                    <td>${post.reg_date}</td>
                    <td>${post.upt_date}</td>
                    <td>${post.view_cnt}</td>
                    <td class="checkbox-column">
                        <input type="checkbox" name="selectedPosts" value="${post.seq}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="삭제">
    </form>

    <c:choose>
        <c:when test="${empty listOfPosts}">
            <!-- 데이터가 없을 경우 -->
            <p>데이터가 없습니다.</p>
        </c:when>
    </c:choose>
</body>
</html>
