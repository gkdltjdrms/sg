<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import= "board.dao.*" %>

    
    <%
    boolean result = false;
    
    if((boolean)session.getAttribute("memId")){
    	result = true;
    }
    else 
    	result = false;
  	
    
  
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	System.out.println("id");
    
 %>
</body>
</html>