<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//data
	String name = null;
	String login_id = null;
	
	
	
	
	name = (String)session.getAttribute("memName");
	login_id = (String)session.getAttribute("memId");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="../image/image2.png" width="120" height="100" alt="망상토끼"
 onclick="location.href='../index.jsp'" style="cursor: pointer">

		<h3><%=name%>님이 로그인 하였습니다</h3>
		
		<!-- 
		<input type="button" value="홈" onclick="location.href='../index.jsp'">
		<input type="button" value="회원정보 수정" onclick="location.href='updateForm.jsp'">
		<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
		<input type="button" value="회원탈퇴" onclick="location.href='deleteForm.jsp'">
		 -->

 <script>
        // 1초 뒤 index.jsp로 이동하는 함수
        function movePage() {
            location.href='../index.jsp';
        }
        
        // 로그인 후 1초 뒤 페이지 이동
        setTimeout("movePage()", 1000);
    </script>


</body>
</html>