<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter" %>
<%@ page import="member.been.MemberDTO" %>
<%@ page import="member.dao.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

<style type="text/css">
        div {
            font-size: 10px;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body onload="pick()">

<%
	//데이터
		String login_id = (String)session.getAttribute("memId");
		
	
	// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = memberDAO.getMember(login_id);
		
	
%>
           


<form name="updateForm" method="post" action="update.jsp">
    <table border="1" cellpadding="5" cellspacing="0" style="width: 500px;">
     
    <img src="../image/image2.png" width="120" height="100" alt="망상토끼"
     onclick="location.href='../index.jsp'" style="cursor: pointer">
        
        
        <tr>

<th>이름</th>

<td><input type="text" name="name" id="name"

value="<%=memberDTO.getName()%>" style="width: 100px">

<div id="nameDiv"></div></td>

</tr>

<tr>
    <th>아이디</th>
    <td><input type="text" name="id" id="id" value="<%=memberDTO.getId()%>" readonly></td>
    <div id="idDiv"></div>
</tr>

<tr>

<th>비밀번호</th>

<td><input type="password" name="pwd" id="pwd" size="40">

<div id="pwdDiv"></div></td>

</tr>

<tr>

<th width="70">재확인</th>

<td><input type="password" name="repwd" id="repwd" size="40"

placeholder="비밀번호 재입력">

<div id="pwdDiv"></div></td>

</tr>

<tr>

<th>성별</th>

<td><input type="radio" name="gender" id="gender_m" value="0"

checked="checked"> <label for="gender_m">남자</label> &nbsp;

<input type="radio" name="gender" id="gender_w" value="1">

<label for="gender_w">여자</label></td>

</tr>

<tr>

<th>이메일</th>

<td><input type="text" name="email1" id="email1"

style="width: 120px" value="<%=memberDTO.getEmail1()%>"> @ <input type="text" name="email2"

id="email2" value="<%=memberDTO.getEmail2()%>" style="width: 120px"> <select

style="width: 100px;" name="email3" id="email3" onchange="select()">

<!-- 자바스크립트 함수 호출 -->

<option value="">직접입력</option>

<option value="naver.com">naver.com</option>

<option value="gmail.com">gmail.com</option>

<option value="nate.com">nate.com</option>

</select></td>

</tr>

<tr>

<th>휴대폰</th>

<td><select style="width: 60px;" name="tel1" id="tel1">

<option value="010">010</option>

<option value="011">011</option>

<option value="019">019</option>

<option value="070">070</option>

</select> - <input style="width: 70px;" value="<%=memberDTO.getTel2()%>" type="text" maxlength=4; name="tel2" id="tel2">

- <input style="width: 70px;" value="<%=memberDTO.getTel3()%>" maxlength=4; type="text" name="tel3" id="tel3">

</td>

</tr>

<tr>

<th>주소</th>

<td><input type="text" name="zipcode" id="zipcode" value="<%=memberDTO.getZipcode()%>"

placeholder="우편번호" size="5" readonly> <input type="button"

value="우편번호검색" onclick="execDaumPostcode()"> <br> <input type="text"

name="addr1" id="addr1" placeholder="기본주소" value="<%=memberDTO.getAddr1()%>" style="width: 400px;"

readonly> <input type="text" name="addr2" id="addr2" value="<%=memberDTO.getAddr2()%>"

placeholder="상세주소" style="width: 400px;"></td>

</tr>


<tr>
			<td colspan ="2" align="center">
			<input type="button" value="회원정보수정" onclick="checkUpdate()"> 
			<input type="reset" value="다시작성" >
			</td>
		</tr>




<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
</script>

<!-- <script type="text/javascript" src="http://localhost:8080/memberServlet2/js/member.js"> -->

<script type="text/javascript" src="../js/member.js"></script>

<script type="text/javascript"> 
function pick() {
	document.updateForm.gender[<%=memberDTO.getGender()%>].checked = true;
	document.updateForm.tel1.value = '<%=memberDTO.getTel1()%>'
	
}

</script>



        
</body>
</html>