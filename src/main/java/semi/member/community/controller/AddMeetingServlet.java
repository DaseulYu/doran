package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.Community;

@WebServlet("/community/addMeeting")
public class AddMeetingServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/community/addMeeting.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String communityName = req.getParameter("communityName");
		String categoryName = req.getParameter("categoryName");
		String communityInfo = req.getParameter("communityInfo");
		String[] address = req.getParameterValues("communityArea");
		
		String communityArea = null;
		if(!address[0].equals("")) {
			communityArea = String.join(",,", address);
		}
		
		Community com = new Community();
		
		com.setCommunityName(communityName);
		com.setCommunityArea(communityArea);
		com.setCommunityInfo(communityInfo);
		
		try {
			CommunityService service = new CommunityService();
			
			int result = service.addMeeting(com, categoryName);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "모임 생성을 완료하였습니다.");
				
			} else {
				session.setAttribute("message", "모임 생성에 실패하였습니다.");
			}
			resp.sendRedirect(req.getContextPath());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
