<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import= "board.dao.*" %>
<%@page import= "board.been.*" %>

    
    <%
 	//data
 	int seq = Integer.parseInt(request.getParameter("seq"));
 	int pg = Integer.parseInt(request.getParameter("pg"));
 	
 	//DB
 	BoardDAO boardDAO = BoardDAO.getInstance();
 	BoardDTO boardDTO = boardDAO.getBoard(seq);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
        div {
            font-size: 8px;
            color: red;
            font-weight: bold;
        }
        
        
    </style>
    
     <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 16px;
            color: #333;
            background-color: white;
        }

        h1 {
            font-size: 28px;
            font-weight: bold;
            color: #333;
            margin: 20px 0;
        }

        table {
            border-collapse: collapse;
            width: 800px;
            margin: 20px auto;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        th {
            font-size: 20px;
            color: #333;
            background-color: #f5f5f5;
            text-align: left;
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        td {
            font-size: 16px;
            color: #666;
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        pre {
            white-space: pre-wrap;
            word-wrap: break-word;
            font-family: Arial, sans-serif;
            font-size: 16px;
            color: #333;
            line-height: 1.5;
        }

        input[type="button"] {
            border: none;
            background-color: #4CAF50;
            color: #fff;
            font-size: 16px;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="button"]:hover {
            background-color: #3e8e41;
        }

        img {
            width: 120px;
            height: 100px;
            cursor: pointer;
        }
    </style>

<img src="../image/image2.png" width="120" height="100" alt="망상토끼"
				onclick="location.href='../index.jsp'" style="cursor: pointer">
				작성한 글확인
</head>
<body>
<%if(boardDTO != null){ %>
    <table border="1" cellpadding="5" cellspacing="0" style="width: 800px;">
        <tr>
            <th style="font-size: 20px; color: black; text-align: center;">
                제목 :<%=boardDTO.getSubject()%>
            </th>
        </tr>
        <tr>
            <td style="text-align: center;">
                글번호: <%=boardDTO.getSeq()%> &nbsp;&nbsp; 
                작성자: <%=boardDTO.getName()%>
            </td>
        </tr>
        <tr>
            <td>
            내용 :
                <pre><%=boardDTO.getContent()%></pre>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="목록" onclick="location.href='boardList.jsp?pg=<%=pg%>'">
            </td>
        </tr>
    </table>
<% } %>

</body>
</html>