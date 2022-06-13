package semi.member.admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.admin.model.service.NoticeListService;

@WebServlet("/notice")
public class NoticeListAllServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			int cp = 1;
			if( req.getParameter("cp") != null ) {
				cp = Integer.parseInt(req.getParameter("cp"));
			}

			NoticeListService service = new NoticeListService();

			Map<String, Object> map = null;

			if( req.getParameter("key") == null ) {
				map = service.noticeListAll(cp);
			} else { // 검색
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				
				map = service.searchNoticeListAll(cp, key, query);
			}
			req.setAttribute("map", map);

			String path = "/WEB-INF/views/admin-page/notice.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
