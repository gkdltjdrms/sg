<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- URL: http://localhost:8080/memberServlet/member/writeForm.html -->

<!-- URL은 웹브라우저상에서 파일의 위치를 알려주는 것임. -->

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

<style type="text/css">

div {

color: red;

font-size: 8pt;

font-weight: bold;

}

</style>

</head>

<body>

<form name="writeForm" method="post"

action="write.jsp">

<table border="1" cellpadding="5" cellspacing="0"

style="width: 500px;">

<tr>

<th>이름</th>

<td><input type="text" name="name" id="name"

placeholder="이름 입력" style="width: 100px">

<div id="nameDiv"></div></td>

</tr>

<tr>

<th>아이디</th>

<td><input type="text" name="id" id="id" placeholder="아이디 입력"

size="30">

<div id="idDiv"></div></td>

</tr>

<tr>

<th>비밀번호</th>

<td><input type="password" name="pwd" id="pwd" size="40"

placeholder="비밀번호 입력">

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

style="width: 120px"> @ <input type="text" name="email2"

id="email2" style="width: 120px"> <select

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

</select> - <input style="width: 70px;" type="text" maxlength=4; name="tel2" id="tel2">

- <input style="width: 70px;" maxlength=4; type="text" name="tel3" id="tel3">

</td>

</tr>

<tr>

<th>주소</th>

<td><input type="text" name="zipcode" id="zipcode"

placeholder="우편번호" size="5" readonly> <input type="button"

value="우편번호검색" onclick="execDaumPostcode()"> <br> <input type="text"

name="addr1" id="addr1" placeholder="기본주소" style="width: 400px;"

readonly> <input type="text" name="addr2" id="addr2"

placeholder="상세주소" style="width: 400px;"></td>

</tr>

<tr>

<td colspan="2" align="center">


<input type="button"

value="회원가입" onclick="checkWrite()"> <input type="reset"

value="다시작성">

<input type="button" value="로그인" onclick="location.href='http://localhost:8080/memberJSP/member/loginForm.jsp';">

</td>

</tr>

</table>

</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- <script type="text/javascript" src="http://localhost:8080/memberServlet2/js/member.js"> -->

<script type="text/javascript" src="../js/member.js"></script>


</body>

</html>

<!-- name, id, password, repassword, gender, email1/2/3, tel1/2/3, zipcode, addr1/2 010 011 019

직접입력 naver.com gmail.com nate.com -->