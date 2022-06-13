package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.admin.model.service.NoticeListService;
import semi.member.community.model.service.CommunityBoardService;


@WebServlet ("/community/board/delete")
public class CommunityBoardDelete extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int boardNo = Integer.parseInt(req.getParameter("no"));
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			
			int result = new CommunityBoardService().deleteBoard(boardNo, communityNo);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			
			if(result > 0) {
				message = "게시글이 삭제되었습니다.";
				path = "list?cn="+communityNo+ "&type=1";
				// /board/list?cn=1&type=1
			} else {
				message = "게시글 삭제 실패";
				path = req.getHeader("referer");
			}
			session.setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
