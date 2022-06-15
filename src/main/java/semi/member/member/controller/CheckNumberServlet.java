package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.member.model.service.MemberService;

@WebServlet("/member/checkNumber")
public class CheckNumberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			String cNumber = req.getParameter("cNumber");
			String inputEmail = req.getParameter("inputEmail");
			
			int result = new MemberService().checkNumber(inputEmail, cNumber);
			
			resp.getWriter().print(result);
			
		} catch (Exception e) {
			
		}
	}
}
