package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.category.model.vo.Category;
import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.Community;
import semi.member.member.model.vo.Member;

@WebServlet("/community/addMeeting")
public class AddMeetingServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/community/addMeeting.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String communityName = req.getParameter("groupName");
		String categoryName = req.getParameter("groupCategory");
		String communityInfo = req.getParameter("groupIntroduce");
		String[] address = req.getParameterValues("regionCategory");
		
		String communityArea = null;
		
		if(!address[0].equals("")) {
			communityArea = String.join(",,", address);
		}

		Community com = new Community();

		com.setCommunityName(communityName);
		com.setCommunityArea(communityArea);
		com.setCommunityInfo(communityInfo);
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		try {
			CommunityService service = new CommunityService();
			
			int result = service.addMeeting(com, categoryName, memberNo);
			
			if(result > 0) {
				session.setAttribute("message", "모임 생성을 완료하였습니다.");
				
			} else {
				session.setAttribute("message", "모임 생성에 실패하였습니다.");
			}
			
			String path = "/WEB-INF/views/community/addMeeting.jsp";
			
			resp.sendRedirect(req.getContextPath());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
