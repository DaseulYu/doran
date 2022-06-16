package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.BoardSignupService;
import semi.member.community.model.service.CommunityBoardService;
import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.BoardSignup;
import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityBoard;
import semi.member.member.model.vo.Member;

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
				List<BoardSignup> bSignup = new BoardSignupService().selectSignupListMain(communityNo);
				req.setAttribute("bSignup", bSignup);
				
				// 모임후기
				List<CommunityBoard> rList = new CommunityBoardService().selectReviewListMain(communityNo);
				req.setAttribute("rList", rList);
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
