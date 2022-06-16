package semi.member.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.admin.model.service.ReportService;

@WebServlet("/report/select")
public class ReportServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			int boardNo = Integer.parseInt(req.getParameter("no"));
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			
			ReportService service = new ReportService();
			
			int result = service.selectReport(communityNo, boardNo, memberNo);
			
			
			
			
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
