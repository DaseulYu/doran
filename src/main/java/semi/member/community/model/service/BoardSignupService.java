package semi.member.community.model.service;


import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.member.common.Util;
import semi.member.community.model.dao.BoardSignupDAO;
import semi.member.community.model.vo.BoardSignup;

public class BoardSignupService {
	
	private BoardSignupDAO dao = new BoardSignupDAO();


	/** 가입 인사 목록 조회
	 * @param communityNo
	 * @return sList
	 * @throws Exception
	 */
	public List<BoardSignup> selectSignupList(int communityNo) throws Exception {

		Connection conn = getConnection();
		
		List<BoardSignup> sList = dao.selectSignupList(conn, communityNo);
		
		close(conn);
		
		return sList;
	}

	/** 가입 인사 등록
	 * @param signup
	 * @return result
	 * @throws Exception
	 */
	public int insertSignup(BoardSignup signup) throws Exception {
		Connection conn = getConnection();
		
		signup.setSignupContent(Util.XSSHandling(signup.getSignupContent()));
		signup.setSignupContent(Util.newLineHandling(signup.getSignupContent()));
		
		int result = dao.insertSignup(conn, signup);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 가입 인사 삭제
	 * @param signupNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteSignup(int signupNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteSignup(conn, signupNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 가입 인사 수정
	 * @param signupNo
	 * @param signupContent
	 * @return result
	 * @throws Exception
	 */
	public int updateSignup(int signupNo, String signupContent) throws Exception {
		Connection conn = getConnection();
		
		signupContent = Util.XSSHandling(signupContent);
		signupContent = Util.newLineHandling(signupContent);
		
		int result = dao.updateSignup(conn, signupNo, signupContent);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;

	}
	
	
	/** 메인용 가입 인사 목록 조회
	 * @param communityNo
	 * @return sList
	 * @throws Exception
	 */
	public List<BoardSignup> selectSignupListMain(int communityNo) throws Exception {

		Connection conn = getConnection();
		
		List<BoardSignup> sList = dao.selectSignupListMain(conn, communityNo);
		
		close(conn);
		
		return sList;
	}
	
	
}
