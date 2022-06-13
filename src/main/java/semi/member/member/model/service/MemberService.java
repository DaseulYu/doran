package semi.member.member.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.member.member.model.dao.MemberDAO;
import semi.member.member.model.vo.Member;

/**
 * @author user1
 *
 */
public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	/** 로그인 Service
	 * @param mem
	 * @return loginMember;
	 * @throws Exception
	 */
	public Member login(Member mem) throws Exception{
		
		Connection conn = getConnection();
		Member loginMember = dao.login(conn, mem);
		
		close(conn);
		return loginMember;
	}

	/** 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		close(conn);
		
		
		return result ;
	}

	/**  휴대폰 번호 중복 검사 Service
	 * @param memberPhone
	 * @return result
	 * @throws Exception
	 */
	public int phoneDupCheck(String memberPhone) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.phoneDupCheck(conn,memberPhone);
		
		close(conn);
		return result;
	}

	/** 회원 정보 수정 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, mem);
		
		if(result>0) commit(conn);
		else 		 rollback(conn);
		
		return result;
	}

	/** 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {
	Connection conn = getConnection();
		
		int result = dao.changePw(conn,currentPw, newPw, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
		
	}

}
