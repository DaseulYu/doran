package semi.member.community.model.dao;


import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityGetTogether;
import semi.member.community.model.vo.CommunityMember;

public class CommunityDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CommunityDAO() {
		try {
			prop = new Properties();
			String filePath = CommunityDAO.class.getResource("/semi/member/sql/community-sql.xml").getPath();
			
			prop.loadFromXML( new FileInputStream(filePath) );
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 모임 상세 정보 조회
	 * @param conn
	 * @param communityNo
	 * @return comm
	 * @throws Exception
	 */
	public Community selectCommunity(Connection conn, int communityNo) throws Exception {
		Community comm = null;
		
		try {
			String sql = prop.getProperty("selectCommunity");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, communityNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new Community();
				
				comm.setCommunityNo(rs.getInt(1));
				comm.setCommunityName(rs.getString(2));
				comm.setCommunityImage(rs.getString(3));
				comm.setCommunityInfo(rs.getString(4));
				comm.setCommunityDetail(rs.getString(5));
				comm.setCommunityArea(rs.getString(6));
				comm.setMemberNickname(rs.getString(7));
				comm.setProfileImage(rs.getString(8));
				comm.setCommunityNotice(rs.getString(9));
			}
		}finally {
			close(conn);
			close(pstmt);
		}
		
		return comm;
	}
	
	/** 모임 등록 DAO
	 * @param conn
	 * @param com
	 * @return result
	 * @throws Exception
	 */
	public int addMeeting(Connection conn, Community com) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("addMeeting");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, com.getCommunityName());
			pstmt.setString(2, com.getCommunityInfo());
			pstmt.setString(3, com.getCommunityArea());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 모임 등록2 DAO
	 * @param conn
	 * @param categoryName
	 * @return result 
	 * @throws Exception
	 */
	public int addMeeting2(Connection conn, String categoryName) throws Exception {
		
		int result = 0;
		
		try {
	
			String sql = prop.getProperty("addMeeting2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, categoryName);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임 정보 등록 DAO
	 * @param conn
	 * @param cgt
	 * @return result
	 * @throws Exception
	 */
	public int addSchedule(Connection conn, CommunityGetTogether cgt) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("addSchedule");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cgt.getCommunityDate());
			pstmt.setInt(2, cgt.getRemitedMember());
			pstmt.setString(3, cgt.getCommunityEvent());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임 승인 DAO
	 * @param conn
	 * @param cm
	 * @return result
	 * @throws Exception
	 */
	public int addCommunityMember(Connection conn, CommunityMember cm) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("addCommunityMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cm.getCommunityNo());
			pstmt.setInt(2, cm.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임장 위임 DAO
	 * @param conn
	 * @param com
	 * @return result
	 * @throws Exception
	 */
	public int entrust(Connection conn, Community com) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("entrust");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, com.getCommunityNo());
			pstmt.setInt(2, com.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 멤버 추방 DAO
	 * @param conn
	 * @param cm
	 * @return result
	 * @throws Exception
	 */
	public int memberOut(Connection conn, CommunityMember cm) throws Exception  {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("memberOut");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cm.getCommunityNo());
			pstmt.setInt(2, cm.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
}
