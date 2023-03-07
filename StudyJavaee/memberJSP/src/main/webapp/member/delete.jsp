<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.been.MemberDTO" %>
<%@ page import="member.dao.MemberDAO" %>

<% 
	//data
	String id = (String)session.getAttribute("memId");
		
	//세션
	session.invalidate(); //무효화

	//DB
	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDAO.memberDelete(id);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
hi





<script type="text/javascript">
window.onload=function(){
	alert("회원탈퇴 완료!!");
	location.href = "loginForm.jsp";
	
	
}


</script>

</body>
</html>