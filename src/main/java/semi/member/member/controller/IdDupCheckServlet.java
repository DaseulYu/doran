package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.member.model.service.MemberService2;


@WebServlet("/member/idDupCheck")
public class IdDupCheckServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberId = req.getParameter("memberId");
		
		try {
			MemberService2 service = new MemberService2();
			int result = service.idDupCheck(memberId);
			
			resp.getWriter().print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
