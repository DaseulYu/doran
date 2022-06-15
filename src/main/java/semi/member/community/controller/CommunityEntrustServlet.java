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

@WebServlet("/community/admin/entrust")
public class CommunityEntrustServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String communityAdmin = req.getParameter("communityAdmin");
		int communityNo = Integer.parseInt(req.getParameter("communityNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		Community com = new Community();
		
		com.setCommunityNo(communityNo);
		com.setMemberNo(memberNo);
		com.setCommunityAdmin(communityAdmin);
		
		CommunityService service = new CommunityService();
		
		try {
			
			int result = service.entrust(com);
			
			HttpSession session = req.getSession();
			String path = null;
			String message = null;
			
			if(result > 0) {
				session.setAttribute("message", "위임에 성공하였습니다.");
				
			} else {
				session.setAttribute("message", "위임에 실패하였습니다.");
			}
			
			session.setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
