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
			int type = Integer.parseInt(req.getParameter("type"));
			
			int result = new CommunityBoardService().deleteBoard(boardNo, communityNo, type);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			
			if(result > 0) {
				
				message = "게시글이 삭제되었습니다.";
				if(type==1) {
					path = "list?cn=" + communityNo + "&type=" + type;
					// /board/list?cn=1&type=1
				} 
				if(type==2) {
					path = req.getHeader("referer");
				}
				
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
