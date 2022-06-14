package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import semi.member.community.model.service.SignupService;
import semi.member.community.model.vo.SignupDetail;

@WebServlet("/community/signup/list")
public class CommunitySignup extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			SignupService service = new SignupService();
			List<SignupDetail> detail = service.selectSignupList(communityNo);
			
			System.out.println(communityNo);
			System.out.println(detail);
			

			req.setAttribute("detail", detail);
			
			String path = "/WEB-INF/views/board/board-signup.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	

}