<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="board.dao.BoardDAO" %>
<%@ page import="board.been.BoardDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>

<style type="text/css">
		.subjectA:link { color: black; text-decoration: none;}
		.subjectA:visited { color: black; text-decoration: none;}
		.subjectA:hover { color: blue; text-decoration: underline;}
		.subjectA:active { color: black; text-decoration: none;}
		
	</style>


</head>
<body>
<img src="../image/image2.png" width="120" height="100" alt="망상토끼"
     onclick="location.href='../member/index.jsp'" style="cursor: pointer">
<%

		int pg = Integer.parseInt(request.getParameter("pg"));

//페이징 처리 - 1페이지당 2개씩
/*			startNum	endNum
 *	PG=1	RN>=1 and RN<=2
 * 	PG=2	RN>=3 and RN<=4
 	PG=3	RN>=5 and RN<=6
 */	
		int endNum = pg*5; //10
		int startNum = endNum-4; //6


		// DB
			BoardDAO boardDAO = BoardDAO.getInstance();
			List<BoardDTO> list = boardDAO.boardList(startNum, endNum);

		//총 글수
			int totalA = boardDAO.getTotalA();
			System.out.println(totalA);
			
			//총 페이지 수
			int totalP = (totalA + 4) / 5;
			// (총글수+1) /2
	
	
// 응답
response.setContentType("text/html;charset=UTF-8");

out.println("<html>");
out.println("<style>");
out.println("#currentpagingdiv {float: left; border: 1px red solid; color: red; width: 20px; height: 20px; margin-left: 5px; text-align: center}");
out.println("#pagingdiv {float: left; black solid; color: black; width: 20px; height: 20px; margin-left: 5px; text-align: center}");
out.println("#currentPaging {color: red; text-decoration: none;}");
out.println("#Paging {color: black; text-decoration: none;}");
out.println("</style>");
out.println("<body>");
			




//페이지 번호
for(int i=1; i<=totalP; i++) {
  if(i == pg)
      out.println("<div id='currentPagingDiv'><a id='currentPaging' href='/miniProject_jsp/board/boardList.jsp?pg="+i+"'>"+i+"</a></div>");
  else
      out.println("<div id='PagingDiv'><a id='paging' href='/miniProject_jsp/board/boardList.jsp?pg="+i+"'>"+i+"</a></div>");
  out.println("<br><br>");
}//for end%>




<h1>글 목록</h1>
<table border="1" frame="hsides" rules="rows" cellpadding="5" cellspacing="0">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="150">작성자</th>
		<th width="100">조회수</th>
		<th width="150">작성일</th>
	</tr>
<%if (list != null) { %>
  <%  for (BoardDTO boardDTO : list) {%>
       	<tr>
       		<td align="center"><%=boardDTO.getSeq() %></td>
       		<td><a class="subject" href=""><%=boardDTO.getSubject() %></a></td>
       		<td align="center"><%=boardDTO.getId() %></td>
       		<td align="center"><%=boardDTO.getHit() %></td>
       		<td align="center">
       		<%= new SimpleDateFormat("yyyy.MM.dd.").format(boardDTO.getLogtime())%>
       		</td>
       	</tr>
  <%  }%>
  
<% }%>
 
</table>
</body>
</html>
