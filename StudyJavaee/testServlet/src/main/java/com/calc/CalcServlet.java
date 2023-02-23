package com.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void init() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터
		int X = Integer.parseInt(request.getParameter("X"));
		int Y = Integer.parseInt(request.getParameter("Y"));
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	//출력 스트림 생성
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h3>");
		out.println("결과 : ");
		out.println("</h3>");
		
		out.println(X+"+"+Y+" =" +(X+Y));
		out.println(X+"-"+Y+" =" +(X-Y));
		out.println(X+"*"+Y+" =" +(X*Y));
		out.println(X+"/"+Y+" =" +((double)X/Y));
		
		out.print("<input type='button' value='뒤로' onclick='javascript:history.go(-1)'>");
		out.print("<input type='button' value='뒤로' onclick=location.href='http://localhost:8080/testServlet/input.html'>");
		out.print("<input type='button' value='연령제한'  onclick=location.href='http://localhost:8080/testServlet/param.html'>");
		
		out.println("</body>");
		out.println("</html>");
		
		
	}
	public void destroy() {}
	
}