package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.member.community.model.service.BoardReplyService;
import semi.member.community.model.vo.BoardReply;

@WebServlet("/community/board/reply/*")
public class BoardReplyController extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/community/board/reply/").length()  );
	      
		BoardReplyService service = new BoardReplyService();
		
		try {

			// 댓글 목록 조회
			if(command.equals("selectReplyList")) {
				int boardNo = Integer.parseInt( req.getParameter("no") );
				List<BoardReply> rList = service.selectReplyList(boardNo);
				
				new Gson().toJson(rList, resp.getWriter());
			}
			
			
			// 댓글 등록
			if(command.equals("insert")) {
				
				int boardNo = Integer.parseInt( req.getParameter("no") );
				int memberNo = Integer.parseInt( req.getParameter("memberNo") );
				String replyContent = req.getParameter("replyContent");
				
				BoardReply reply = new BoardReply();
				
				reply.setBoardNo(boardNo);
				reply.setMemberNo(memberNo);
				reply.setReplyContent(replyContent);

				int result = service.insertReply(reply);
				
				resp.getWriter().print(result);
			}
			
			
			// 댓글 삭제
			if(command.equals("delete")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				
				int result = service.deleteReply(replyNo);
				
				resp.getWriter().print(result);
			}
			
			
			// 댓글 수정
			if(command.equals("update")) {
				
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				String replyContent = req.getParameter("replyContent");
				
				int result = service.updateReply(replyNo, replyContent);
				
				resp.getWriter().print(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
