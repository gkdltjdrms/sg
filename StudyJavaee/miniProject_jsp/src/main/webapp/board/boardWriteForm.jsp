<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>게시판 글쓰기</title>
  <style type="text/css">
    body {
      font-family: Arial, sans-serif;
      font-size: 16px;
      line-height: 1.6;
      color: #333;
      background-color: #fff;
      margin: 0;
      padding: 0;
    }
    
    h1 {
      font-size: 32px;
      font-weight: bold;
      margin: 50px 0 30px;
      text-align: center;
    }
    
    form {
      width: 600px;
      margin: 0 auto;
    }
    
    table {
      border-collapse: collapse;
      width: 100%;
      margin-bottom: 30px;
    }
    
    th {
      font-weight: bold;
      vertical-align: top;
      padding: 10px;
      border: 1px solid #ccc;
      background-color: #eee;
    }
    
    td {
      padding: 10px;
      border: 1px solid #ccc;
    }
    
    input[type="text"],
    textarea {
      width: 100%;
      padding: 10px;
      font-size: 16px;
      border-radius: 4px;
      border: 1px solid #ccc;
      box-sizing: border-box;
      resize: vertical;
    }
    
    .error {
      color: red;
      font-weight: bold;
    }
    
    input[type="submit"],
    input[type="reset"] {
      display: inline-block;
      background-color: #333;
      color: #fff;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
      font-weight: bold;
      border-radius: 4px;
      cursor: pointer;
    }
    
    input[type="reset"] {
      margin-left: 20px;
      background-color: #ccc;
      color: #333;
    }
    
  </style>
</head>
<body>

  <h1><img src="../image/image2.png" width="120" height="100" alt="망상토끼" onclick="location.href='../index.jsp'" style="cursor: pointer">
  게시판 글쓰기</h1>
  <form name="boardWriteForm" method="post" action="boardWrite.jsp">
    <table>
      <tr>
        <th>제목</th>
        <td>
          <input type="text" name="subject" id="subject" placeholder="제목을 입력하세요">
          <div id="subjectDiv" class="error"></div>
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea name="content" id="content" rows="10" placeholder="내용을 입력하세요"></textarea>
          <div id="contentDiv" class="error"></div>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="글쓰기" onclick="checkBoardWrite()">
          <input type="reset" value="다시 작성">
        </td>
      </tr>
    </table>
  </form>



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
