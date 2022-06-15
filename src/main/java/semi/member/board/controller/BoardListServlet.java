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

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int cp = 1;

			if (req.getParameter("cp") != null) { // 쿼리스트링에 "cp"가 존재한다면
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			System.out.println("cp : " + cp);
			BoardService service = new BoardService();

			Map<String, Object> map = null;

			if (req.getParameter("key") == null) {

				map = service.selectBoardList(cp);

			}

			req.setAttribute("map", map);

			String path = "/WEB-INF/views/community/communityList.jsp";

			RequestDispatcher dispatcher = req.getRequestDispatcher(path);

			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
