package semi.member.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.member.model.service.MemberService;
import semi.member.member.model.vo.Member;

@WebServlet("/member/findPw")
public class FindPwServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/findPw.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputName = req.getParameter("inputName");
		String inputEmail = req.getParameter("inputEmail");
		String newPw = req.getParameter("newPw");
		
		Member mem = new Member();
		
		mem.setMemberName(inputName);
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(newPw);
		
		
		try {
			MemberService service = new MemberService();
			
			int result = service.findPw(mem);
			
			
			HttpSession session = req.getSession();
			
			String path = null;
			
			if(result > 0) {
				session.setAttribute("message", "새 비밀번호 설정에 성공하였습니다.");
				resp.sendRedirect(req.getContextPath()+"/member/login");
				
			}else {
				session.setAttribute("message", "새 비밀번호 설정에 실패했습니다.");
				resp.sendRedirect(req.getContextPath() + "/member/findPw");
			}
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

