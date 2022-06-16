package semi.member.community.controller;

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

import semi.member.common.MyRenamePolicy;
import semi.member.community.model.service.CommunityBoardService;
import semi.member.community.model.vo.BoardDetail;
import semi.member.community.model.vo.BoardImage;
import semi.member.member.model.vo.Member;

@WebServlet("/community/board/write")
public class BoardWriteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String mode = req.getParameter("mode");
			
			if(mode.equals("update")) {
				
				int communityNo = Integer.parseInt(req.getParameter("cn"));
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				BoardDetail detail = new CommunityBoardService().selectBoardDetail(boardNo, communityNo);
				
				detail.setBoardContent(detail.getBoardContent().replaceAll("<br>", "\n"));
				
				req.setAttribute("detail", detail);
			}
			String path ="/WEB-INF/views/board/board-write.jsp";
			
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
			String folderPath = "/resources/images/board/";
			String filePath = root + folderPath; 
			String encoding = "UTF-8";
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames(); 
			
			List<BoardImage> imageList = new ArrayList<BoardImage>();
			
			while(files.hasMoreElements()) {
				
				String name = files.nextElement();
				String rename = mpReq.getFilesystemName(name);
				String original = mpReq.getOriginalFileName(name);
				
				if(rename != null) {
					BoardImage image = new BoardImage();
					
					image.setImageOriginal(original);
					image.setImageName(folderPath+rename);
					image.setImageLevel(Integer.parseInt(name));
					
					imageList.add(image);
				}
			}
			
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int boardCode = Integer.parseInt(mpReq.getParameter("type"));
			int communityNo = Integer.parseInt(mpReq.getParameter("cn"));
			
			
			
			// 로그인 정보 얻어오기
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			
			BoardDetail detail = new BoardDetail();
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			CommunityBoardService service = new CommunityBoardService();
			
			String mode = mpReq.getParameter("mode");
			
			if(mode.equals("insert")) {
				int boardNo = service.insertBoard(detail, imageList, boardCode, communityNo, memberNo);
				
				String path = null;
				String message = null;
				
				if(boardNo > 0) { // 성공
					session.setAttribute("message", "게시글이 등록되었습니다.");
					
					if(boardCode==1) {
						path = "detail?no=" + boardNo + "&type=" + boardCode + "&cn=" + communityNo;
					} else {
						path = "review?type=" + boardCode + "&cn=" + communityNo;
					}
					
				} else { // 실패
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode="+ mode +"&cn=" + communityNo + "&type=" + boardCode;
				}
				resp.sendRedirect(path);
			}
			
			if(mode.equals("update")) {
				int boardNo = Integer.parseInt(mpReq.getParameter("no"));
				String deleteList = mpReq.getParameter("deleteList");
				
				int cp = 1;
				if( req.getParameter("cp") != null ) { 
					cp = Integer.parseInt(req.getParameter("cp"));
				}
				
				detail.setBoardNo(boardNo);
				
				int result = service.updateBoard(imageList, detail, deleteList);
				
				String path = null;
				String message = null;
				
				if(result > 0) { // 성공
					message = "게시글이 수정되었습니다.";
					
					if(boardCode==1) {
						path = "detail?no=" + boardNo + "&type=" + boardCode + "&cn=" + communityNo + "&cp=" + cp;
					} 
					if(boardCode==2) {
						path = "review?type=" + boardCode + "&cn=" + communityNo + "&cp=" + cp;
					}
					
				} else { // 실패
					message = "게시글 수정 실패";
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
