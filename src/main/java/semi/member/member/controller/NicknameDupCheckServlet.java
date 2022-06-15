package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.member.model.service.MemberService;

@WebServlet("/member/myPage/nicknameDupCheck")
public class NicknameDupCheckServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memberNickname = req.getParameter("memberNickname");

		try {
			MemberService service = new MemberService();
			int result = service.nicknameDupCheck(memberNickname);
			
			resp.getWriter().print(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
