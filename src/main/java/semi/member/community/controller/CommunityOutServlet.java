package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityMember;
import semi.member.member.model.vo.Member;

@WebServlet("/community/admin/out")
public class CommunityOutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int communityNo = Integer.parseInt(req.getParameter("cn"));
		
		CommunityService service = new CommunityService();
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo();
		
		try {
			int result = service.memberOut(communityNo, memberNo);
			
			if(result > 0) {
				session.setAttribute("message", "멤버 추방에 성공하였습니다.");
				
			} else {
				session.setAttribute("message", "멤버 추방에 실패하였습니다.");
			}
			
			String path = "/WEB-INF/views/community/meeting-admin.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
