package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.CommunityMember;

@WebServlet("/community/admin/refuse")
public class CommunityRefuseServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/community/meeting-admin.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int communityNo = Integer.parseInt(req.getParameter("cn"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		try {
			
			CommunityService service = new CommunityService();
			
			int result = service.refuseCommunityMember(communityNo, memberNo);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "거절하였습니다.");
				
			} else {
				session.setAttribute("message", "거절에 실패하였습니다.");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
