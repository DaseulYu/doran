//package semi.member.board.controller;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import semi.member.board.model.service.BoardService;
//import semi.member.community.model.vo.BoardDetail;
//
//@WebServlet("/community/detail")
//public class BoardDetailServlet extends HttpServlet{
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		try {
//			int boardNo = Integer.parseInt(req.getParameter("cn"));
//			
//			BoardService service  =new BoardService();
//			
//			BoardDetail board = service.selectBoardDetail(boardNo);
//			
//			String path = "/WEB-INF/views/community/communityDetail.jsp";
//			
//			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
//			
//			dispatcher.forward(req, resp);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
