package semi.member.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.board.model.service.BoardService;

@WebServlet("/community/category")
public class BoardCategoryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int type = Integer.parseInt( req.getParameter("type"));
			
			int cp = 1;
			System.out.println("type : " + type);
			if (req.getParameter("cp") != null) { 
				cp = Integer.parseInt(req.getParameter("cp"));
				System.out.println("cp : " + cp);
			}
			BoardService service = new BoardService();

			Map<String, Object> map = null;

			if (req.getParameter("query") == null) {

				map = service.selectCategoryList(type, cp);
			} else {

				String query = req.getParameter("query");
				map = service.searchCategoryList(cp, query, type);
			}

			req.setAttribute("map", map);

			String path = "/WEB-INF/views/community/category.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
