package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.member.community.model.dao.SignupDAO;
import semi.member.community.model.vo.SignupDetail;

public class SignupService {
	
	private SignupDAO dao = new SignupDAO();

	/** 가입 인사 목록 조회
	 * @param communityNo
	 * @return detail
	 * @throws Exception
	 */
	public List<SignupDetail> selectSignupList(int communityNo) throws Exception {
		Connection conn = getConnection();
		
		List<SignupDetail> detail = dao.selectSignupList(conn, communityNo);
		
		close(conn);
		
		return detail;
	}
	
	

}
