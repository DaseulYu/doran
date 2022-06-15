package semi.member.member.model.service;

import static semi.member.common.JDBCTemplate.close;
import static semi.member.common.JDBCTemplate.commit;
import static semi.member.common.JDBCTemplate.getConnection;
import static semi.member.common.JDBCTemplate.rollback;

import java.sql.Connection;

import semi.member.member.model.dao.MemberDAO2;
import semi.member.member.model.vo.Member;

public class MemberService2 {
	private MemberDAO2 dao = new MemberDAO2();

	
	/**
	 * 회원가입 Service
	 * 
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception{
		Connection conn = getConnection();

		int result = dao.signUp(conn, mem);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}
}
