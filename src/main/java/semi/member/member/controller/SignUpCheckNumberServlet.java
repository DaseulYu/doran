package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.member.model.service.MemberService2;


@WebServlet("/member/signUpCheckNumber")
public class SignUpCheckNumberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			String cNumber = req.getParameter("cNumber");
			String memberId = req.getParameter("memberId");
			
			int result = new MemberService2().signUpcheckNumber(memberId, cNumber);
			
			resp.getWriter().print(result);
			
		} catch (Exception e) {
			
		}
	}
}
