package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.member.model.service.MemberService2;
import semi.member.member.model.vo.Member;

@WebServlet("/member/signUp")
public class signUpServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String memberEmail = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
		String memberGender = req.getParameter("memberGender");
		String memberPhone = req.getParameter("memberTel");
		String memberNickname = req.getParameter("memberNickname");
		String memberBirth = req.getParameter("memberBirth");
		String memberLive = req.getParameter("memberLive");
		
		Member mem = new Member();
		
		mem.setMemberEmail(memberEmail);
		mem.setMemberPw(memberPw);
		mem.setMemberName(memberName);
		mem.setMemberGender(memberGender);
		mem.setMemberPhone(memberPhone);
		mem.setMemberNickname(memberNickname);
		mem.setMemberBirth(memberBirth);
		mem.setMemberLive(memberLive);
		
		try {
			MemberService2 service = new MemberService2();
			
			int result = service.signUp(mem);
			
			
			HttpSession session = req.getSession();
			
			if(result > 0) { // 성공
				session.setAttribute("message", "회원 가입 완료");
			}else { // 실패
				session.setAttribute("message", "회원 가입 실패");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
