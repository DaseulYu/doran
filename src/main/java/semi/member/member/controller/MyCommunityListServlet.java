package semi.member.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.member.member.model.service.MemberService;
import semi.member.member.model.vo.Member;

@WebServlet("/member/myPage/myCommuList")
public class MyCommunityListServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String path = "/WEB-INF/views/member/myCommunityList.jsp";
         req.getRequestDispatcher(path).forward(req, resp);
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
   
      try {
         MemberService service = new MemberService();
         List<Member> commulist = service.myCommunityList(memberNo);

         new Gson().toJson(commulist, resp.getWriter());

      } catch (Exception e) {

         e.printStackTrace();

         resp.setStatus(500);
         resp.getWriter().print(e.getMessage());
      }
   }

}