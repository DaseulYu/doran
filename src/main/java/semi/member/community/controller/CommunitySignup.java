package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.community.model.service.BoardSignupService;
import semi.member.community.model.vo.BoardSignup;

@WebServlet("/community/signup/list")
public class CommunitySignup extends HttpServlet {
	
//	가입인사 조회 게시판
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int communityNo = Integer.parseInt( req.getParameter("cn") );
				
			List<BoardSignup> sList = new BoardSignupService().selectSignupList(communityNo);
			req.setAttribute("sList", sList);

			
			String path = "/WEB-INF/views/board/board-signup.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}