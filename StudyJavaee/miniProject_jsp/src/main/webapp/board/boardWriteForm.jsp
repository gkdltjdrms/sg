<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
<style type="text/css">
        div {
            font-size: 8px;
            color: red;
            font-weight: bold;
        }
    </style>

</head>
<body>
<form name="boardWriteForm" method="post" action="boardWrite.jsp">
	<table border="1" cellpadding="5" cellspacing="0">
			
			<img src="../image/image2.png" width="70" height="50" alt="망상토끼"
				onclick="location.href='../member/index.jsp'" style="cursor: pointer">
				
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="subject" id="subject" placeholder="제목 입력" size="50">
					<div id="subjectDiv"></div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" id="content" cols="50" rows="15"></textarea>
					<div id="contentDiv"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글쓰기" onclick="checkBoardWrite()">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</form>
	</table>

	<script type="text/javascript">
	function checkBoardWrite(){

		document.getElementById("subjectDiv").innerText = "";
		document.getElementById("contentDiv").innerText = "";


		if(document.getElementById("subject").value == ""){
			document.getElementById("subjectDiv").innerText = "제목 입력";
			document.getElementById("subject").focus();
		}else if (document.getElementById("content").value == ""){
			document.getElementById("contentDiv").innerText = "내용 입력";
			document.getElementById("content").focus();
		}else{
			document.boardWriteForm.submit();
		}
	}
	</script>
</body>
</html>
