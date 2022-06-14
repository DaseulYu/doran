package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.member.community.model.dao.CommunityDAO;
import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityGetTogether;
import semi.member.community.model.vo.CommunityMember;

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
	
	/** 모임 등록 Service
	 * @param com
	 * @param categoryNo
	 * @return result
	 * @throws Exception
	 */
	public int addMeeting(Community com, String categoryName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.addMeeting(conn, com);
		
		if(result > 0) {
			result = dao.addMeeting2(conn, categoryName);
		}
		
		if(result > 0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 모임 정모 등록 Service
	 * @param cgt
	 * @return result
	 * @throws Exception
	 */
	public int addSchedule(CommunityGetTogether cgt) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.addSchedule(conn, cgt);
		
		if(result > 0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 모임 승인 Service
	 * @param cm
	 * @return result
	 * @throws Exception
	 */
	public int addCommunityMember(CommunityMember cm) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.addCommunityMember(conn, cm);
		
		
		if(result > 0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 모임장 위임 Service
	 * @param com
	 * @return result
	 * @throws Exception
	 */
	public int entrust(Community com) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.entrust(conn, com);
		
		if(result > 0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 멤버 추방 Service
	 * @param cm
	 * @return result
	 * @throws Exception
	 */
	public int memberOut(CommunityMember cm) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.memberOut(conn, cm);
		
		if(result > 0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

}
