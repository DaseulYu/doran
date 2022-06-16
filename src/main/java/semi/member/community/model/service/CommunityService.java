package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.member.community.model.dao.CommunityDAO;
import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityApply;
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
	public int addMeeting(Community com, String categoryName, int memberNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.addMeeting1(conn, com, memberNo);

		if(result > 0) {
			result = dao.addMeeting2(conn, categoryName);
		}

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임장 - 회원 승인 Service
	 * @param communityNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int addCommunityMember(int communityNo, int memberNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.addCommunityMember(conn, communityNo, memberNo);

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
	public int memberOut(int communityNo, int memberNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.memberOut(conn, communityNo, memberNo);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 가입된 회원 목록 조회 Service
	 * @return commMemberList
	 * @throws Exception
	 */
	public List<CommunityMember> selectCommMember(int communityNo) throws Exception {

		Connection conn = getConnection();

		List<CommunityMember> commMemberList = dao.selectCommMember(conn, communityNo);

		close(conn);

		return commMemberList;
	}

	/** 가입 신청 회원 목록 조회 Service
	 * @param communityNo
	 * @return applyMemberList
	 * @throws Exception
	 */
	public List<CommunityApply> selectApllyMember(int communityNo) throws Exception {

		Connection conn = getConnection();

		List<CommunityApply> commApplyList = dao.selectApllyMember(conn, communityNo);

		close(conn);

		return commApplyList;
	}


	/** 모임 삭제 Service
	 * @param communityNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteCommunity(int communityNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.deleteCommunity(conn, communityNo);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임 대표 이미지 변경 Service
	 * @param communityNo
	 * @param communityImage
	 * @return result
	 * @throws Exception
	 */
	public int updateCommunityImage(Community comm) throws Exception {

		Connection conn = getConnection();

		int result = dao.updateCommunityImage(conn, comm);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임장 위임 Service
	 * @param com
	 * @param memberNo
	 * @return return
	 * @throws Exception
	 */
	public int entrust(int communityNo, int memberNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.entrust(conn, communityNo, memberNo);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임 가입하기
	 * @param memberNo
	 * @param communityNo
	 * @return result
	 * @throws Exception
	 */
	public int communityJoin(int memberNo, int communityNo) throws Exception {
		Connection conn = getConnection();

		int result = dao.communityJoin(conn, memberNo, communityNo);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임 탈퇴하기
	 * @param memberNo
	 * @param communityNo
	 * @return result
	 * @throws Exception
	 */
	public int communitySecession(int memberNo, int communityNo) throws Exception {
		Connection conn = getConnection();

		int result = dao.communitySecession(conn, memberNo, communityNo);

		if(result > 0) commit(conn);
		else         rollback(conn);

		close(conn);

		return result;
	}

	/** 모임 이름 조회 Service
	 * @param comm
	 * @return result
	 * @throws Exception
	 */
	public Community selectCommunityName(int communityNo) throws Exception {
		Connection conn = getConnection();
		
		Community comm = dao.selectCommunityName(conn, communityNo);
		
		close(conn);
		
		return comm;
	}
	
}
