package semi.member.member.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.member.member.model.service.MemberService;

@WebServlet("/member/myPage/currentPwConfirmCheck")
public class CurrentPwConfirmCheckServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPw = req.getParameter("currentPw");
		int loginMemberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
		
		try {
			MemberService service = new MemberService();
			int result = service.currentPwConfirmCheck(currentPw, loginMemberNo);
			
			resp.getWriter().print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	
	}

}
