package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.community.model.service.CommunityService;
import semi.member.member.model.vo.Member;

@WebServlet("/community/join/*")
public class CommunityJoinServlet extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/community/join/").length()  );
		
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			
			if(command.equals("insert")) {
				int result = new CommunityService().communityJoin(memberNo, communityNo);
				resp.getWriter().print(result);
			}
			
			if(command.equals("delete")) {
				int result = new CommunityService().communitySecession(memberNo, communityNo);
				resp.getWriter().print(result);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	
	}
	
	

}
