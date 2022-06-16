package semi.member.community.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.community.model.service.CommunityBoardService;

@WebServlet("/community/board/review")
public class CommunityReview extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			int type = Integer.parseInt(req.getParameter("type"));

			int cp = 1;
			if( req.getParameter("cp") != null ) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			CommunityBoardService service = new CommunityBoardService();
			
			Map<String, Object> map = service.communityBoardList(cp, type, communityNo);
			
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/board/board-review.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
