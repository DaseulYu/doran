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


	/** 인증 번호 DB 추가
	 * @param memberId
	 * @param cNumber
	 * @return 
	 * @throws Exception
	 */
	public int insertCertification(String memberId, String cNumber) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateCertification(conn, memberId, cNumber);
		
		if(result == 0) {
			result = dao.insertCertification(conn, memberId, cNumber);
			
		}
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 인증번호 확인 Service
	 * @param memberId
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int signUpcheckNumber(String memberId, String cNumber) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.checkNumber(conn, memberId, cNumber);
		
		close(conn);
		
		return result;
	}


	/** 아이디 중복 확인 Service
	 * @param inputEmail
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception{
		Connection conn = getConnection(); // DBCP 에서 만들어둔 커넥션 얻어오기
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn); 
		
		return result;
	}
}
