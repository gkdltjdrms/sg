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
//      만약, Get방식이면 doGet으로 수정.
      
      // 데이터
      String seq = request.getParameter("seq"); 
     
      
      // DB
      GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
      GuestbookDTO guestbookDTO = guestbookDAO.guestbookSearch(seq);
      
      // 응답
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter(); // 스트림 생성
      // 지금의 out 객체는 웹으로 보낸다 그러면 브라우저 번역을 해서 화면 결과를 보여준다.
      out.println("<html>");
      out.println("<body>");
      out.println("<form>");
     
      if(guestbookDTO != null) {
    	  out.println("<table border='1' cellpadding='5' cellspacing='0'>");
    	  out.println("<tr>");
         out.println("<td>작성자</td>");
         out.println("<td>"+ guestbookDTO.getName()+"</td>");
         out.println("<td>작성일</td>");
         out.println("<td>"+ guestbookDTO.getLogtime()+"</td>");
         out.println("</tr>");
         
         out.println("<tr>");
         out.println("<td>이메일</td>");
         out.println("<td colspan='3'>"+ guestbookDTO.getEmail()+"</td>");
         out.println("</tr>");
         
         out.println("<tr>");
         out.println("<td>홈페이지</td>");
         out.println("<td colspan='3'>"+ guestbookDTO.getHomepage()+"</td>");
         out.println("</tr>");
         
         out.println("<tr>");
         out.println("<td>제목</td>");
         out.println("<td colspan='3'>"+ guestbookDTO.getSubject()+"</td>");
         out.println("</tr>");
        
         out.println("<tr>");
         out.println("<td colspan='4'>"+ guestbookDTO.getContent()+"</td>");
         out.println("</tr>");
         
         out.println("<input type='button' value='뒤로가기' onclick='history.back()'>");

         out.println("</table>");
      } else { /* null값으로 나와 보완 필요......... */
         out.println("<h3>글번호가 없습니다</h3>");
         out.println("<input type='button' value='뒤로가기' onclick='history.back()'>");

      }
      out.println("</table>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
   }
   
}