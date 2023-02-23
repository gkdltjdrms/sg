package com.param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	//출력 스트림 생성
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h3>");
		
		out.println("나의 이름은 "+ name +"이고 나이는 "+ age+ "살이므로");
		if(age >=20) out.println("성인 입니다.");
		else out.print("청소년 입니다.");
		out.println("</h3>");
		out.println("</body>");
		out.println("</html>");
		
		
	}
	public void destroy() {}
	
}
