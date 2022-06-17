package semi.member.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName = "loginFilter", 
		   urlPatterns = {"/board/write", "/board/delete", 
				   		  "/reply/insert", "/reply/update", "/reply/delete",
				   		  "/member/myPage/*", "/admin/*",
				   		  "/community/signup/list", "/community/board/list", "/community/board/review",
				   		  "/community/admin"

})
public class LoginFilter extends HttpFilter implements Filter {
       
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("로그인 필터 생성");
	}
	
	public void destroy() {
		System.out.println("로그인 필터가 변경되어 파괴 -> 이후 재생성");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 클라이언트 요청 시 로그인 여부를 확인
		// -> 로그인 되어있지 않으면 메인 페이지로 리다이렉트
		
		// 1. ServletRequest, ServletResponse 다운 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// 2. Session 얻어오기
		HttpSession session = req.getSession();
		
		// 3. 로그인 되어있는지 확인
		if(session.getAttribute("loginMember")!=null) { // 로그인이 되어있는 경우

			// 다음 필터 호출, 없으면 servlet으로 이동
			chain.doFilter(request, response);
			
		} else {
			session.setAttribute("message", "비정상적인 접근입니다."); 
			
			resp.sendRedirect(req.getContextPath()); // 메인 페이지로 이동
		}
		
		
	}


}
