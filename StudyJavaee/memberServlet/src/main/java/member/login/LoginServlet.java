package member.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.been.MemberDTO;
import member.dao.MemberDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 데이터
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");

		// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO dto = memberDAO.memberRead(login_id, login_pw);
//		String name = memberDAO.memberLogin(login_id, login_pwd);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); //call
		out.println("<html>" + "<body>");
		// 응답
		if (dto == null || !(dto.getId().equals(login_id)) || !(dto.getPwd().equals(login_pw))) {
			out.println("<h3>로그인 실패</h3>");
			out.println("아이디 혹은 비밀번호가 맞지않습니다");
			out.println("<input type='button' value='뒤로' onclick='history.go(-1)'>");
		} else {
			out.println("<h3>로그인 성공</h3>");
			out.println(dto.getName()+"님이 로그인 하였습니다");
			
		}
		out.println("</body>" + "</html>");

	}
}