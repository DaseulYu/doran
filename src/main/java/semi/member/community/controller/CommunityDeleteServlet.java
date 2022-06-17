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

@WebServlet("/community/admin/delete")
public class CommunityDeleteServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
		try {
			
			int communityNo = Integer.parseInt(req.getParameter("cn"));
			System.out.println("communityNo : "+communityNo);

			CommunityService service = new CommunityService();
			
			int result = service.deleteCommunity(communityNo); 
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "모임이 삭제되었습니다.");
				
			} else {
				session.setAttribute("message", "모임 삭제에 실패하였습니다.");
			}
			
			String path = "/WEB-INF/views/community/meeting-admin.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
