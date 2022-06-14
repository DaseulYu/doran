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
import semi.member.community.model.vo.BoardDetail;
import semi.member.community.model.vo.BoardReply;

@WebServlet("/community/board/detail")
public class CommunityBoardDetail extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			int boardNo = Integer.parseInt(req.getParameter("no"));
			
			CommunityBoardService service = new CommunityBoardService();

			BoardDetail detail = service.selectBoardDetail(boardNo, communityNo);
			
			// 댓글 조회
			if(detail != null) {
				List<BoardReply> rList = new BoardReplyService().selectReplyList(boardNo);
				req.setAttribute("rList", rList);
			}
					
			req.setAttribute("detail", detail);
			
			String path = "/WEB-INF/views/board/board-detail.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
