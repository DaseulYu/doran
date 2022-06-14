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
import semi.member.community.model.vo.CommunityMember;

@WebServlet("/community/admin/out")
public class CommunityOutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int communityNo = Integer.parseInt(req.getParameter("communityNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		String memberFlag = req.getParameter("memberFlag");
		int memberCount = Integer.parseInt(req.getParameter("memberCount"));
		
		CommunityMember cm = new CommunityMember();
		
		cm.setCommunityNo(communityNo);
		cm.setMemberNo(memberNo);
		cm.setMemberFlag(memberFlag);
		cm.setMemberCount(memberCount);
		
		CommunityService service = new CommunityService();
		
		try {
			int result = service.memberOut(cm);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			
			if(result > 0) {
				session.setAttribute("message", "멤버 추방에 성공하였습니다.");
				
			} else {
				session.setAttribute("message", "멤버 추방에 실패하였습니다.");
			}
			
			session.setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
