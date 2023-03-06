<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="member.been.MemberDTO" %>
<%@ page import="member.dao.MemberDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<div style="text-align: center">
    <form action="" method="post">
        <label for="depwd">비밀번호 입력</label>
        <input type="password" name="depwd" id="depwd" size="20"> 
        <input type="button" value="검색" onclick="checkpwd()">
        <div id="depwdDiv"></div>
    </form>
</div>

<%
    // 데이터
    String login_id = (String)session.getAttribute("memId");

    // DB
    MemberDAO memberDAO = MemberDAO.getInstance();
    MemberDTO memberDTO = memberDAO.getMember(login_id);
%>

<script type="text/javascript">
    function checkpwd() {
        if (memberDTO.getPwd() != depwd) {
            document.getElementById("depwdDiv").innerText = "비밀번호가 맞지 않습니다";
        }
    }
</script>

</body>
</html>