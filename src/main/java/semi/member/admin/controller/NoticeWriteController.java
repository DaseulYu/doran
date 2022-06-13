package semi.member.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import semi.member.admin.model.service.NoticeListService;
import semi.member.admin.model.vo.NoticeDetail;
import semi.member.admin.model.vo.NoticeImage;
import semi.member.common.MyRenamePolicy;


@WebServlet("/admin/write")
public class NoticeWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String mode = req.getParameter("mode");
			
			if(mode.equals("update")) {
				
				int noticeNo = Integer.parseInt( req.getParameter("no"));
				
				NoticeDetail detail = new NoticeListService().selectNoticeDetail(noticeNo);
				
				detail.setNoticeContent(detail.getNoticeContent().replaceAll("<br>", "\n"));
				
				req.setAttribute("detail", detail);
			}
			String path ="/WEB-INF/views/admin-page/notice-write.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int maxSize = 1024 * 1024 * 50;
			
			HttpSession session = req.getSession(); 
			String root = session.getServletContext().getRealPath("/");
			String folderPath = "/resources/images/notice/";
			String filePath = root + folderPath; 
			String encoding = "UTF-8";
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames(); 
			
			// 이미지 업로드
			List<NoticeImage> imageList = new ArrayList<NoticeImage>();
			
			while(files.hasMoreElements()) {
				
				String name = files.nextElement();
				String rename = mpReq.getFilesystemName(name);
				String original = mpReq.getOriginalFileName(name);
				
				if(rename != null) {
					NoticeImage image = new NoticeImage();
					
					image.setImageOriginal(original);
					image.setImageName(folderPath+rename);
					image.setImageLevel(Integer.parseInt(name));
					
					imageList.add(image);
				}
			}
			
			String noticeTitle = mpReq.getParameter("boardTitle");
			String noticeContent = mpReq.getParameter("boardContent");
			int noticeCode = Integer.parseInt(mpReq.getParameter("type"));
			
			
			
			// 로그인 정보 얻어오기
//			Member loginMember = (Member)session.getAttribute("loginMember");
//			int memberNo = loginMember.getMemberNo();
			
			
			NoticeDetail detail = new NoticeDetail();
			
			detail.setNoticeTitle(noticeTitle);
			detail.setNoticeContent(noticeContent);
//			detail.setMemberNo(memberNo);
			
			
			NoticeListService service = new NoticeListService();
			
			String mode = mpReq.getParameter("mode");
			
  			// 게시글 등록
			if(mode.equals("insert")) {
				int noticeNo = service.insertNotice(detail, imageList, noticeCode);
				
				String path = null;
				String message = null;
				
				if(noticeNo > 0) { // 성공
					message = "게시글이 등록되었습니다.";
					
					// doran/admin/detail?no=57
					path = "detail?no=" + noticeNo;
					
				} else { // 실패
					message = "게시글 등록 실패";
					
					// doran/admin/write?mode=insert
					path = "write?mode=" + mode + "&type=" + noticeCode;
				}
				session.setAttribute("message", message);
				resp.sendRedirect(path);
			}
			
			
			
			// 게시글 수정
			if(mode.equals("update")) {
				int noticeNo = Integer.parseInt(mpReq.getParameter("no"));
				int cp = Integer.parseInt(mpReq.getParameter("cp"));
				String deleteList = mpReq.getParameter("deleteList");
				
				detail.setNoticeNo(noticeNo);
				
				int result = service.updateNotice(imageList, detail, deleteList, noticeCode);
				
				String path = null;
				String message = null;
				
				if(result > 0) { // 성공
					message = "게시글이 수정되었습니다.";
					
					// doran/admin/detail?no=57&cp=1
					path = "detail?no=" + noticeNo + "&type=" + noticeCode + "&cp=" + cp;
					
				} else { // 실패
					message = "게시글 수정 실패";
					
					// write?mode=update&type=1&cp=1&no=70
					path = req.getHeader("referer");
				}
				session.setAttribute("message", message);
				resp.sendRedirect(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
