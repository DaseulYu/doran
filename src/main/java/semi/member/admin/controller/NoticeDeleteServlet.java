package semi.member.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.admin.model.service.NoticeListService;

@WebServlet("/admin/delete")
public class NoticeDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int noticeNo = Integer.parseInt(req.getParameter("no"));
			int result = new NoticeListService().deleteNotice(noticeNo);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			// /doran/admin/detail?no=73&cp=1
			
			if(result > 0) {
				message = "게시글이 삭제되었습니다.";
				path = "list?cp=1";
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
