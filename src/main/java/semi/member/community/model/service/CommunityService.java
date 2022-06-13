package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.member.community.model.dao.CommunityDAO;
import semi.member.community.model.vo.Community;

public class CommunityService {
	
	private CommunityDAO dao = new CommunityDAO();

	/** 모임 상세 정보 조회
	 * @param communityNo
	 * @return comm
	 * @throws Exception
	 */
	public Community selectCommunity(int communityNo) throws Exception {
		
		Connection conn = getConnection();
		
		Community comm = dao.selectCommunity(conn, communityNo);
		
		close(conn);
		
		return comm;
	}

}
