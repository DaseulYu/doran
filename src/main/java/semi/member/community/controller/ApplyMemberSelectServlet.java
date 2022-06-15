package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.CommunityApply;

@WebServlet("/community/admin/Applyselect")
public class ApplyMemberSelectServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try { 
			
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			
			CommunityService service = new CommunityService();
			
			List<CommunityApply> applyMemberList = service.selectApllyMember(communityNo);
			
			req.setAttribute("applyMemberList", applyMemberList);
			
			String path = "/WEB-INF/views/community/meeting-admin.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
