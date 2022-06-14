package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import semi.member.member.model.service.MemberService;
import semi.member.member.model.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String path = "/WEB-INF/views/member/login.jsp";
	
	req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		Member mem = new Member();
		
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(inputPw);
		
		
		try {
			MemberService service = new MemberService();
			
			Member loginMember = service.login(mem);
			
			HttpSession session = req.getSession();
			
			if(loginMember !=null) {// 로그인 성공
				session.setAttribute("loginMember", loginMember);
				
				session.setMaxInactiveInterval(3600);
				
			Cookie c = new Cookie("saveId", inputEmail);
			if(req.getParameter("saveId")!=null) {
				c.setMaxAge(60*60*24*30);
			}else {
				c.setMaxAge(0);
			}
			c.setPath(req.getContextPath());
			

			resp.addCookie(c);
			}else {//로그인 실패
			session.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
			}

			resp.sendRedirect(req.getContextPath());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
