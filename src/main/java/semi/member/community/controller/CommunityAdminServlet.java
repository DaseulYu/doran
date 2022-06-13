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

@WebServlet("/community/admin")
public class CommunityAdminServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int communityNo = Integer.parseInt(req.getParameter("COMMUNITY_NO"));
		int memberNo = Integer.parseInt(req.getParameter("MEMBER_NO"));
		String memberFL = req.getParameter("MEMBER_FL");
		int memberCount = Integer.parseInt(req.getParameter("MEMBERCOUNT"));
		
		try {
			CommunityMember cm = new CommunityMember();
			
			cm.setCommunityNo(communityNo);
			cm.setMemberNo(memberNo);
			cm.setMemberFL(memberFL);
			cm.setMemberCount(memberCount);
			
			CommunityService service = new CommunityService();
			
			int result = service.addCommunityMember(cm);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "승인하였습니다.");
				
			} else {
				session.setAttribute("message", "승인에 실패하였습니다.");
			}
			
			resp.sendRedirect(req.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
