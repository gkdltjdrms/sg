package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;


@WebServlet("/GuestbookSearchServlet")
public class GuestbookSearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");

		String email = request.getParameter("email");

		String homepage = request.getParameter("homepage");

		String subject = request.getParameter("subject");

		String content = request.getParameter("content");
		
		GuestbookDTO guestbookDTO = new GuestbookDTO();

		guestbookDTO.setName(name);

		guestbookDTO.setEmail(email);

		guestbookDTO.setHomepage(homepage);

		guestbookDTO.setSubject(subject);

		guestbookDTO.setContent(content);
		
		// DB와 연동

		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();

		guestbookDAO.guestbookWrite(guestbookDTO);
		
		
		// 응답

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter(); // 스트림 생성

		  out.println("<html>");
		    out.println("<body>");
		    out.println("<table>");
		    out.println("<tr>");
		    out.println("<th>작성자</th>");
		    out.println("<th>작성일</th>");
		    out.println("<th>이메일</th>");
		    out.println("<th>홈페이지</th>");
		    out.println("<th>제목</th>");
		    out.println("<th>내용</th>");
		    out.println("</tr>");
		
		
		
		
	}

}
