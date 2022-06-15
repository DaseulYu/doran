package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.Community;

@WebServlet("/community/admin/delete")
public class CommunityDeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 모임장 위임
		String communityAdmin = req.getParameter("communityAdmin");
		int communityNo = Integer.parseInt(req.getParameter("communityNo"));
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));

		
		// 모임 상태
		String communityFlag = req.getParameter("communityFlag");
		
		Community com = new Community();
		
		com.setCommunityNo(communityNo);
		com.setMemberNo(memberNo);
		com.setCommunityAdmin(communityAdmin);          
		
		CommunityService service = new CommunityService();
		
		try {
			
			int update = service.entrust(com); // 모임장 위임 update문 
			
//			if(update > 0) { // 모임장 상태 바뀜 (위임 기록 컬럼 삭제)
//				int delete = service.deleteEntrust();
//			} else { // 모임장 상태 안 바뀜(위임 대상 없음) -> 모임 삭제 
//				int result = service.deleteCommunity()
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
