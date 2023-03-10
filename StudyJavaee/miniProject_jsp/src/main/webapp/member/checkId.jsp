<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import= "member.dao.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	//data
	String id = request.getParameter("id"); // 클라이언트에서 전송한 아이디값을 가져온다.

	//DB
	MemberDAO memberDAO = MemberDAO.getInstance();
	boolean existId = memberDAO.isExistId(id); //아이디가 있다/true/사용 불가능
%>


</head>
<body>
<% if(existId) {%>
	<form action="">
	<h3><%=id %>는 사용 불가능</h3>
	<br>
	아이디 <input type="text" name="id"> <input type="submit" value="중복체크">
	</form>
	<%}else{ %>
	<h3><%=id %> 사용 가능</h3>
	<input type="button" value="사용하기" onclick="checkIdClose('<%=id%>')">
	
	<%}%>


 <script type="text/javascript">
           
 function checkIdClose(id){
	alert("하이");
	    // 아이디 사용 가능 시 창 닫기 및 부모 창에 값 전달
	    window.opener.writeForm.id.value = id;
	    opener.writeForm.pwd.focus();
	    window.close();
	}
 
 //중복체크를 할때 빈공간 일시 다시 적는 함수
 var submitBtn = document.querySelector("input[type='submit']");
 submitBtn.addEventListener("click", function() {
     var idInput = document.querySelector("input[name='id']");
     if (!idInput.value) {
         alert("아이디를 적어주세요.");
         event.preventDefault();
     }
 });
 
 
 </script>


</body>
</html>



