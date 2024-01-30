<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="util.BoardStatusConverter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>

<style>
    /* CSS for mouseover effect on table rows */
    tbody tr:hover {
        background-color: #f5f5f5; /* Change this to your desired hover color */
    }
</style>



<head>

<meta charset="EUC-KR">
<title>Login Success</title>
</head>
<body>
	<%-- Check if session attributes are present --%>
	<c:if test="${empty sessionScope.loggedInUserId}">
		<%-- Redirect to payLogin.jsp if session attributes are not present --%>
		<% response.sendRedirect(request.getContextPath() + "/payLogin.jsp"); %>
	</c:if>

	<h1>로그인 성공</h1>
	<%
    // 세션에서 로그인한 사용자의 정보를 가져옵니다.
    String loggedInUserId = (String) session.getAttribute("loggedInUserId");
    String loggedInUserName = (String) session.getAttribute("loggedInUserName");
    String loggedInUserRank = (String) session.getAttribute("loggedInUserRank");
%>



	<div id="head">
		<h3><%= loggedInUserName %>(<%= loggedInUserRank %>) 님 환영합니다
		</h3>
		<form action="logout" method="post">
			<button type="submit">로그아웃</button>
		</form>

		<c:choose>
			<c:when
				test="${loggedInUserRank eq '과장' or loggedInUserRank eq '부장'}">
				<button>대리 결재</button>
			</c:when>
			<c:otherwise>
				<%-- Do nothing or display alternative content for other ranks --%>
			</c:otherwise>
		</c:choose>

		<div>
			<form action="payWriteForm" method="get">
				<input type="hidden" name="id" value="${loggedInUserId}"> <input
					type="hidden" name="userName" value="${loggedInUserName}">
				<input type="hidden" name="userRank" value="${loggedInUserRank}">
				<button type="submit">글쓰기</button>
			</form>

		</div>
	</div>

	<!-- 검색창 부분 -->
	<form id="searchForm" action="search" method="get" accept-charset="UTF-8">
	<input type="text" name="id" value="${loggedInUserId}" readonly>
		<select name="searchType">
			<option value="all">선택</option>
			<option value="writer">작성자</option>
			<option value="approver">결재자</option>
			<option value="titleContent">제목+내용</option>
		</select> <input type="text" name="searchKeyword" placeholder="검색어 입력">

		<!-- 추가: 결재상태 드롭다운 메뉴 -->
		<select name="approveStatus" id="approveStatus">
			<option value="">결재상태</option>
			<option value="save">임시저장</option>
			<option value="wait">결재대기</option>
			<option value="checking">결재중</option>
			<option value="finish">결재완료</option>
			<option value="rejected">반려</option>
		</select>
		
		  <!-- 추가: 시작일과 종료일 입력 -->
		   <br><label  for="startDate">시작일:</label>
		    <input type="date" name="startDate" id="startDate">
		
		    <label for="endDate">종료일:</label>
		    <input type="date" id="endDate" name="endDate">

		<button type="submit">검색</button>
	</form>


	<!-- 보드 리스트 부분 -->
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>결재일</th>
				<th>결재자</th>
				<th>결재상태</th>
			
				
			</tr>
		</thead>
		<tbody>
	

		<%-- 여기에 서버에서 가져온 보드 리스트 데이터를 반복해서 출력하는 부분이 들어갑니다. --%>
			<c:forEach var="board" items="${boardList}">
		    <tr>
		        <td>${board.seq}</td>
		        <td>${board.memName}</td>
		        <td>${board.subject}</td>
		        <td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${board.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  <%--       <td>${board.checkName}</td> --%>
		         <td>${board.checkBoardName}</td>
		        <td>${BoardStatusConverter.getKoreanStatus(board.payOption)}</td>
		    </tr>
		</c:forEach>


			
		</tbody>
	</table>
	<!-- jQuery를 로드하는 스크립트 추가 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function() {

    	// 페이지 로드 시 검색어를 불러와서 검색 필드에 설정
        var searchKeyword = localStorage.getItem('searchKeyword');
        if (searchKeyword) {
            $('#searchKeyword').val(searchKeyword);
        }
        
     // approveStatus의 값이 변경될 때마다 이벤트 처리
        $('#approveStatus').on('change', function() {
            // 여기에서 AJAX 요청 수행
            updateSearch();
        });


     // 테이블 행 클릭 이벤트 처리
        $('tbody').on('click', 'tr', function() {
            // 선택한 행에서 seq 값을 가져옴
            var seq = $(this).find('td:first').text(); // 첫 번째 td 열에 있는 값 (seq 값)
            
            // 서버로 이동하는 form 동적 생성
            var form = $('<form></form>');
            form.attr('method', 'GET');
            form.attr('action', 'detail');

            // seq 값을 form에 추가
            var seqField = $('<input></input>');
            seqField.attr('type', 'hidden');
            seqField.attr('name', 'seq');
            seqField.attr('value', seq);

            var userRankField = $('<input></input>');
            userRankField.attr('type', 'hidden');
            userRankField.attr('name', 'userRank');
            userRankField.attr('value', '<%= loggedInUserRank %>'); // 서버에서 가져온 userRank 값

            // form을 body에 추가
            form.append(seqField);
            $('body').append(form);

            // form 제출
            form.submit();
        });


        
        
    });

    function updateSearch() {
        var form = document.getElementById('searchForm');

        // AJAX 요청에 대한 데이터 구성
        var formData = {
            id: form.id.value,
            searchType: form.searchType.value,
            searchKeyword: form.searchKeyword.value,
            approveStatus: form.approveStatus.value,
            startDate: form.startDate.value,
            endDate: form.endDate.value
        };

        // AJAX 호출하여 검색 결과 업데이트
        $.ajax({
            url: "searchoption",
            type: 'GET',
            async: true,
            data: formData,
            dataType: "json", // JSON 응답을 기대함
            contentType: "application/json",
            success: function(data) {
                // 서버로부터 받은 JSON 데이터를 사용하여 필요한 작업 수행
                console.log(data); // 개발자 도구의 콘솔에 로그 출력
                console.log(JSON.stringify(data));

                // 받은 데이터를 이용하여 테이블 업데이트
                var tbody = $('tbody');
                tbody.empty(); // 테이블 내용 초기화

                // 받은 데이터를 이용하여 테이블에 행 추가
                for (var i = 0; i < data.length; i++) {
                    var checkDate = data[i].checkDate ? new Date(data[i].checkDate).toLocaleString() : ''; // 결재일이 있는 경우에만 포맷 적용
                    var checkName = data[i].checkName || ''; // 결재자가 있는 경우에만 사용, 없으면 빈 문자열

                    var row = '<tr><td>' + data[i].seq +
                        '</td><td>' + data[i].memName +
                        '</td><td>' + data[i].subject +
                        '</td><td>' + new Date(data[i].regDate).toLocaleString() +
                        '</td><td>' + checkDate +
                        '</td><td>' + checkName +
                        '</td><td>' + data[i].payOption +
                        '</td></tr>';
                    tbody.append(row);
                }
            },
            error: function(request, status, error) {
                console.log(error); // 개발자 도구의 콘솔에 에러 로그 출력
                alert('실패3');
            }
        });

        return false;
    }
</script>
	

</body>
</html>
