package semi.member.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.member.community.model.service.BoardSignupService;
import semi.member.community.model.vo.BoardSignup;

@WebServlet("/community/board/signup/*")
public class BoardSignupController extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/community/board/signup/").length()  );
	      
		BoardSignupService service = new BoardSignupService();
		
		try {

			// 가입 인사 목록 조회
			if(command.equals("selectSignupList")) {
				int communityNo = Integer.parseInt( req.getParameter("cn") );
				List<BoardSignup> sList = service.selectSignupList(communityNo);
				
				new Gson().toJson(sList, resp.getWriter());
			}
			
			

			// 가입 인사 등록
			if(command.equals("insert")) {
				
				int communityNo = Integer.parseInt( req.getParameter("cn") );
				int memberNo = Integer.parseInt( req.getParameter("memberNo") );
				String signupContent = req.getParameter("signupContent");
				
				BoardSignup signup = new BoardSignup();
				
				signup.setCommunityNo(communityNo);
				signup.setMemberNo(memberNo);
				signup.setSignupContent(signupContent);
				
				int result = service.insertSignup(signup);
				
				resp.getWriter().print(result);
			}
			
			
			// 가입 인사 삭제
			if(command.equals("delete")) {
				int signupNo = Integer.parseInt( req.getParameter("signupNo") );
				int result = service.deleteSignup(signupNo);
				resp.getWriter().print(result);
			}
			
			
			// 가입 인사 수정
			if(command.equals("update")) {
				int signupNo = Integer.parseInt( req.getParameter("signupNo") );
				String signupContent = req.getParameter("signupContent");
				
				int result = service.updateSignup(signupNo, signupContent); 
				
				resp.getWriter().print(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
