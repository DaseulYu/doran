package semi.member.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.admin.model.service.ReportService;
import semi.member.member.model.vo.Member;

@WebServlet("/community/board/report/*")
public class ReportServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			int memberNo = loginMember.getMemberNo();
			
			int communityNo = 0;
			int boardNo = 0;

			if(req.getParameter("cn") != null) {
				communityNo = Integer.parseInt(req.getParameter("cn"));
			}
			if(req.getParameter("boardNo") != null) {
				boardNo = Integer.parseInt(req.getParameter("boardNo"));
			}
					

			String reportTitle = req.getParameter("reportTitle");
			
			ReportService service = new ReportService();
			
			int result = service.insertReport(communityNo, boardNo, memberNo, reportTitle);

//			System.out.println(communityNo);
//			System.out.println(boardNo);
			String path = null;
			String message = null;
			
			if(result > 0) {
				message = "신고 처리되었습니다.";
				path = req.getHeader("referer");
			} else {
				message = "신고 처리 실패";
				path = req.getHeader("referer");
			}
			session.setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
