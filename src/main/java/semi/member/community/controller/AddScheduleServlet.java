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
import semi.member.community.model.vo.CommunityGetTogether;

@WebServlet("/community/addSchedule")
public class AddScheduleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/community/addMeeting-schedule.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String communityEvent = req.getParameter("communityEvent");
		String communityDate = req.getParameter("communityDate");
		int remitedMember = Integer.parseInt(req.getParameter("remitedMember"));
		String meetingArea = req.getParameter("meetingArea");
		
		CommunityGetTogether cgt = new CommunityGetTogether();
		
		cgt.setCommunityEvent(communityEvent);
		cgt.setCommunityDate(communityDate);
		cgt.setRemitedMember(remitedMember);
		cgt.setMeetingArea(meetingArea);
		
		try {
			CommunityService service = new CommunityService();
			
			int result = service.addSchedule(cgt);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "모임 정모 생성을 완료하였습니다.");
				
			} else {
				session.setAttribute("message", "모임 정모 생성에 실패하였습니다.");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
