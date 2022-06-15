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

@WebServlet("/member/myPage/info")
public class MyPageInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/myPage-info.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	// 회원 정보 수정 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memberNickname = req.getParameter("memberNickname");
		String memberPhone = req.getParameter("memberPhone");

		HttpSession session = req.getSession();

		Member loginMember = (Member) (session.getAttribute("loginMember"));

		int memberNo = loginMember.getMemberNo();

		Member mem = new Member();

		mem.setMemberNo(memberNo);
		mem.setMemberNickname(memberNickname);
		mem.setMemberPhone(memberPhone);

		try {
			MemberService service = new MemberService();
			int result = service.updateMember(mem);

			if (result > 0) {
				session.setAttribute("message", "회원 정보 수정이 정상적으로 처리되었습니다.");

				loginMember.setMemberNickname(memberNickname);
				loginMember.setMemberPhone(memberPhone);
			} else {
				session.setAttribute("message", "회원 정보 수정에 실패하였습니다.");
			}
			resp.sendRedirect(req.getContextPath() + "/member/myPage/info");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
