package semi.member.member.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

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

	/** 현재 비밀번호 확인 Service
	 * @param currentPw
	 * @param loginMemberNo
	 * @return result
	 * @throws Exception
	 */
	public int currentPwConfirmCheck(String currentPw, int loginMemberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.currentPwConfirmCheck(conn, currentPw, loginMemberNo);
		
		close(conn);
		return result;
	}

	/** 프로필 이미지 변경 Service
	 * @param memberNo
	 * @param profileImage
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(int memberNo, String profileImage) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateProfileImage(conn, memberNo,profileImage);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}
	
	/** 인증 번호 DB 추가 Service
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 * @throws Exception
	 */

	public int insertCertification(String inputEmail, String cNumber) throws Exception {
		
		Connection conn = getConnection();
		
		// 1) 입력한 이메일과 일치하는 값이 있으면 수정(UPDATE)
		int result = dao.updateCertification(conn, inputEmail, cNumber);
		
		// 2) 일치하는 이메일이 없는겨우 -> 처음으로 인증번호를 발급 받음 -> 삽입(INSERT)
		if( result == 0 ) {
			result = dao.insertCertification(conn, inputEmail, cNumber);
		}
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}


	
	/** 인증번호 확인 Service
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 * @throws Exception
	 */
	public int checkNumber(String inputEmail, String cNumber) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.checkNumber(conn, inputEmail, cNumber);
		
		close(conn);
		return result;
	}

	/** 이메일 확인 Service
	 * @param inputEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail) throws Exception{
		Connection conn = getConnection();
		
		
		int result = dao.emailDupCheck(conn, inputEmail);
		
		close(conn);
		return result;
	}

	/** 비밀번호 재설정 Service
	 * @param inputName
	 * @param inputEmail
	 * @return result
	 * @throws Exception
	 */
	public int findPw(Member mem) throws Exception  {
		Connection conn = getConnection();
		
		int result = dao.findPw(conn, mem); 
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회원 탈퇴 Service
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, memberPw);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Member> myCommunityList(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<Member> commulist = dao. myCommunityList(conn, memberNo);
		
		close(conn);
		return commulist;
	}

}

