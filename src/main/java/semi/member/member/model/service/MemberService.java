package semi.member.member.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.member.member.model.dao.MemberDAO;
import semi.member.member.model.vo.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	/** 로그인 Service
	 * @param mem
	 * @return loginMember;
	 * @throws Exception
	 */
	public Member login(Member mem) throws Exception{
		
		Connection conn = getConnection();
		Member loginMember = dao.login(conn,mem);
		close(conn);
		return loginMember;
	}

}
