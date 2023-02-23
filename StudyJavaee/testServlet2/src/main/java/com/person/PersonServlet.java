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
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<body>");
	out.println("<ul>");
	out.println("<li>이름 : " + name + "</li><br>");
	if (gender == 0) out.println("<li>성별 : 남자" + "</li><br>");
	else out.println("<li>성별 : 여자" + "</li><br>");
	out.println("<li>색깔 : " + color + "</li><br>");
	if (hobbies != null) {
	    StringBuilder sb = new StringBuilder();
	    for (String hobby : hobbies) {
	        sb.append(hobby);
	        sb.append(", ");
	    }
	    String allHobbies = sb.toString();
	    allHobbies = allHobbies.substring(0, allHobbies.length() - 2);
	    out.println("<li>취미 : " + allHobbies + "</li><br>");
	}
	if (subjects != null) {
	    StringBuilder sb = new StringBuilder();
	    for (String subject : subjects) {
	        sb.append(subject);
	        sb.append(", ");
	    }
	    String allSubjects = sb.toString();
	    allSubjects = allSubjects.substring(0, allSubjects.length() - 2); 
	    out.println("<li>과목 : " + allSubjects + "</li><br>");
	}
	out.println("</ul>");
	out.println("</body>");
	out.println("</html>");
	}

}
