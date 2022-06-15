package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.community.model.service.BoardReplyService;
import semi.member.community.model.service.CommunityBoardService;
import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.BoardReply;
import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityBoard;

@WebServlet("/community/detail")
public class CommunityServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			
			CommunityService service = new CommunityService();
			
			Community comm = service.selectCommunity(communityNo);
			
			
			// 게시판별 내용 얻어오기
			if(comm != null) {
				// 자유게시판
				List<CommunityBoard> bList = new CommunityBoardService().selectBoardList(communityNo);
				req.setAttribute("bList", bList);
				
				// 가입인사
				//List<BoardSignup> bReply = new BoardReplyService().selectReplyList(communityNo);
				//req.setAttribute("bReply", bReply);
			}
			
			
			req.setAttribute("comm", comm);
			
			String path = "/WEB-INF/views/community/communityDetail.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
