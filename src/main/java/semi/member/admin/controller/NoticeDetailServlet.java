package semi.member.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.admin.model.service.NoticeListService;
import semi.member.admin.model.vo.NoticeDetail;

@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int noticeNo = Integer.parseInt(req.getParameter("no"));
			
			NoticeListService service = new NoticeListService();
			
			NoticeDetail detail = service.selectNoticeDetail(noticeNo);
			
			req.setAttribute("detail", detail);
			
			String path = "/WEB-INF/views/admin-page/notice-detail.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
