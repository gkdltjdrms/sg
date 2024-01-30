<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 내용</title>

    <!-- 추가된 CSS 스타일 -->
    <style>
        .table-container {
            margin-top: 20px;
        }
        .back-button,
        .edit-button {
            margin-top: 20px;
        }
        .content-container {
            margin-top: 20px;
        }
        .content-cell {
            height: 150px; /* 원하는 높이로 조절 */
            vertical-align: top; /* 셀의 내용을 상단에 정렬 */
        }
    </style>
</head>
<body>
    <h1>글 내용</h1>

    <c:choose>
        <c:when test="${not empty post}">
            <!-- 변경된 테이블 구조 -->
            <table border="1" class="table-container">
                <tr>
                    <th>글 번호</th>
                    <th>작성자 (id)</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>수정일</th>
                    <th>조회수</th>
                </tr>
                <tr>
                    <td>${post.seq}</td>
                    <td>${post.mem_id}</td>
                    <td>${post.board_subject}</td>
                    <td>${post.reg_date}</td>
                    <td>${post.upt_date}</td>
                    <td>${post.view_cnt}</td>
                </tr>
            </table>
            
            <!-- 내용 표시 -->
            <div class="content-container">
                <h2>내용</h2>
                <div class="content-cell">${post.board_content}</div>
            </div>
            
            <!-- 수정 버튼 -->
            <form action="goToUpdate" method="get" class="edit-button">
                <input type="hidden" name="seq" value="${post.seq}">
                <input type="submit" value="수정하기">
            </form>
            
            <!-- 뒤로가기 버튼 -->
            <form action="goToList" method="get" class="back-button">
                <input type="submit" value="뒤로가기">
            </form>
        </c:when>
        <c:otherwise>
            <!-- 데이터가 없을 경우 -->
            <p>해당 글이 없습니다.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
