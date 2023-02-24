package com.person;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// data	
	String name = request.getParameter("name");
	int gender = Integer.parseInt(request.getParameter("gender"));
	String color = request.getParameter("color");
	String[] hobbies = request.getParameterValues("hobby");
	String[] subjects = request.getParameterValues("subject");
	
	// response
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();//브라우저로 나올수있게 해준다.
	out.println("<html>");
	out.println("<body style='background: yellow;'>");
	out.println("<ul style='border: 1px solid red; color: "+color+"'>");
	out.println("<li>이름 : " + name + "</li><br>");
	if (gender == 0) out.println("<li>성별 : 남자" + "</li><br>");
	else out.println("<li>성별 : 여자" + "</li><br>");
	out.println("<li>색깔 : " + color + "</li><br>");
	out.println("<li>취미 :  ");
	if (hobbies != null) {
		for(int i=0; i<hobbies.length; i++) {
			out.print(hobbies[i]+" ");
		}
	}
	out.println("<li>과목 :  ");
	if (subjects != null) {
		for(int i=0; i<subjects.length; i++) {
			out.print(subjects[i]+" ");
		}
	}
	  

	out.println("</ul>");
	out.println("</body>");
	out.println("</html>");
	}

}
