package semi.member.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import semi.member.common.MyRenamePolicy;
import semi.member.community.model.service.CommunityService;
import semi.member.community.model.vo.Community;

@WebServlet("/community/detail/image")
public class CommunityImageServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int maxSize = 1024 * 1024 * 20;
			
			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String folderPath = "/resources/images/community/";
			String filePath = root + folderPath;
			
			String encoding = "UTF-8";
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			int communityNo = Integer.parseInt(mpReq.getParameter("cn"));
			String communityImage = folderPath + mpReq.getFilesystemName("communityImage");
			
			Community comm = new Community();
			
			comm.setCommunityNo(communityNo);
			comm.setCommunityImage(communityImage);
			
			CommunityService service = new CommunityService();
			
			int result = service.updateCommunityImage(comm);
			
			if(result > 0) {
				session.setAttribute("message", "모임 대표 이미지가 변경되었습니다.");
				
			} else {
				session.setAttribute("message", "모임 대표 이미지 변경 실패");
			}
			
			String path = "/WEB-INF/views/community/communityDetail.jsp";
			
			resp.sendRedirect(req.getContextPath());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
