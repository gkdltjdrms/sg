<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>

<style>
.pagination {
	display: flex;
	list-style: none;
	padding: 0;
}

.pagination-item {
	margin: 0 5px;
}

.pagination-item a {
	text-decoration: none;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	color: #333;
}

.pagination-item span {
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	color: #fff;
	background-color: red;
}
</style>

</head>
<body>
	<h1>글목록</h1>
	<!-- 글쓰기 버튼 -->
	<form action="goToWrite" method="get">
		<input type="submit" value="글쓰기">
	</form>

	<form action="goDelete" method="get">
		<input type="submit" value="삭제">
	</form>

	<!-- 검색 기능 구현 -->
	<form id="searchForm">
		<input type="text" id="keyword" name="keyword"
			placeholder="검색어를 입력하세요..." value="${param.keyword}">

		<!-- 검색 옵션 추가 -->
		<select id="searchOption" name="searchOption">
			<option value="all"
				<c:if test="${param.searchOption == 'all'}">selected</c:if>>전체</option>
			<option value="title"
				<c:if test="${param.searchOption == 'title'}">selected</c:if>>제목</option>
			<option value="titleAndAuthor"
				<c:if test="${param.searchOption == 'titleAndAuthor'}">selected</c:if>>제목+작성자</option>
			<option value="id"
				<c:if test="${param.searchOption == 'id'}">selected</c:if>>id</option>
		</select>

		<!-- Additional input fields for date range -->
		<label for="startDate">시작 날짜:</label> <input type="date"
			id="startDate" name="startDate" placeholder="시작 날짜"
			value="${param.startDate}"> <label for="endDate">종료
			날짜:</label> <input type="date" id="endDate" name="endDate"
			placeholder="종료 날짜" value="${param.endDate}"> 
	</form>
	 <button id="btn_Search">검색</button>
	<table border="1">
		<tr>
			<th>글 번호</th>
			<th>작성자 (id)</th>
			<th>작성자 이름</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${listOfPosts}" var="post">
			<tr>
				<td>${post.seq}</td>
				<td>${post.mem_id}</td>
				<td>${post.mem_name}</td>
				<td>
					<!-- 글 제목에 링크 추가 --> <a href="listinfo?seq=${post.seq}">${post.board_subject}</a>
				</td>
				<td>${post.reg_date}</td>
				<td>${post.upt_date}</td>
				<td>${post.view_cnt}</td>
			</tr>
		</c:forEach>
	</table>

	<!-- 페이지 번호 표시 -->
	<c:if test="${totalPages > 1}">
		<ul class="pagination">
			<c:if test="${currentPage > 1}">
				<li class="pagination-item"><a
					href="?page=1&keyword=${param.keyword}&searchOption=${param.searchOption}&startDate=${param.startDate}&endDate=${param.endDate}">처음</a></li>
				<li class="pagination-item"><a
					href="?page=${currentPage - 1}&keyword=${param.keyword}&searchOption=${param.searchOption}&startDate=${param.startDate}&endDate=${param.endDate}">이전</a></li>
			</c:if>

			<c:forEach begin="${currentPage - 2 < 1 ? 1 : currentPage - 2}"
				end="${currentPage + 2 > totalPages ? totalPages : currentPage + 2}"
				var="pageNumber">
				<li class="pagination-item"><c:choose>
						<c:when test="${pageNumber eq currentPage}">
							<span>${pageNumber}</span>
						</c:when>
						<c:otherwise>
							<a
								href="?page=${pageNumber}&keyword=${param.keyword}&searchOption=${param.searchOption}&startDate=${param.startDate}&endDate=${param.endDate}">${pageNumber}</a>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>

			<c:if test="${currentPage < totalPages}">
				<li class="pagination-item"><a
					href="?page=${currentPage + 1}&keyword=${param.keyword}&searchOption=${param.searchOption}&startDate=${param.startDate}&endDate=${param.endDate}">다음</a></li>
				<li class="pagination-item"><a
					href="?page=${totalPages}&keyword=${param.keyword}&searchOption=${param.searchOption}&startDate=${param.startDate}&endDate=${param.endDate}">맨끝</a></li>
			</c:if>
		</ul>
	</c:if>

	<c:choose>
		<c:when test="${empty listOfPosts}">
			<!-- 데이터가 없을 경우 -->
			<p>데이터가 없습니다.</p>
		</c:when>
	</c:choose>

	<!-- 수정된 JavaScript 코드 -->
	<!-- jQuery를 사용하기 위해 CDN 추가 -->
<!-- list.jsp -->

<!-- list.jsp -->

<!-- jQuery CDN 추가 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function () {
    $("#btn_Search").on("click", function () {
        alert("clcik");
        $.ajax({
            type: "GET",
            url: "searchgo",
            data: $("#searchForm").serialize(),
            dataType: "json", // 이 부분을 추가해보세요
            contentType: "application/json", // 이 부분을 추가해보세요
            success: function (data) {
                console.log("AJAX Success:", data);
                // TODO: 응답 처리
                
            },
            error: function (error) {
                console.error("Error fetching data:", error);
            }
        });
    });
});




</script>



</body>
</html>
