package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.CommunityMember;
import semi.member.member.model.vo.Member;

@WebServlet("/community/admin/select")
public class SelectCommunityMemberServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try { 
			
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			
			CommunityService service = new CommunityService();
			
			HttpSession session = req.getSession();
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			int memberNo = loginMember.getMemberNo();
			
			List<CommunityMember> commMemberList = service.selectCommMember(communityNo, memberNo);
			
			req.setAttribute("commMemberList", commMemberList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
