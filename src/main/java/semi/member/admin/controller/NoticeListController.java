package semi.member.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.admin.model.service.NoticeListService;

@WebServlet("/admin/list/*")
public class NoticeListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
//		/doran/admin/list/delete
		String contextPath = req.getContextPath();
//		/doran
		String command = uri.substring(  (contextPath + "/admin/list/").length()  );
//		delete
		
		try {
			if(command.equals("delete")) {
				int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
				int result = new NoticeListService().deleteNotice(noticeNo);
				resp.getWriter().print(result);
			}
			
			if(command.equals("insert")) {
				int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
				int result = new NoticeListService().insertNotice2(noticeNo);
				resp.getWriter().print(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	
	}

}
