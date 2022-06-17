package semi.member.community.model.dao;


import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.member.community.model.vo.Community;
import semi.member.community.model.vo.CommunityApply;
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
				comm.setCommunityInfo(rs.getString(3));
				comm.setCommunityNotice(rs.getString(4));
				comm.setCommunityArea(rs.getString(5));
				comm.setMemberNickname(rs.getString(6));
				comm.setProfileImage(rs.getString(7));
				comm.setCommunityAdmin(rs.getInt(8));
				comm.setCommunityImage(rs.getString(9));
			}
		}finally {
			close(rs);
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
	public int addMeeting1(Connection conn, Community com, int memberNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("addMeeting1");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, com.getCommunityName());
			pstmt.setString(2, com.getCommunityInfo());
			pstmt.setString(3, com.getCommunityArea());
			pstmt.setInt(4, memberNo);
			pstmt.setInt(5, com.getCategoryNo());
			
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
	

	/** 모임장 - 회원 승인 DAO
	 * @param conn
	 * @param communityNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int addCommunityMember(Connection conn, int communityNo, int memberNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("addCommunityMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, communityNo);
			
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
	public int memberOut(Connection conn, int communityNo, int memberNo) throws Exception  {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("memberOut");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 가입된 회원 목록 조회 DAO
	 * @param conn
	 * @return commMemberList
	 * @throws Exception
	 */
	public List<CommunityMember> selectCommMember(Connection conn, int communityNo) throws Exception {
		
		List<CommunityMember> commMemberList = new ArrayList<CommunityMember>();
		
		try {
			String sql = prop.getProperty("selectCommMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityMember cm = new CommunityMember();
				
				cm.setMemberNo(rs.getInt(1));
				cm.setMemberNickname(rs.getString(2));
				cm.setMemberProfileImage(rs.getString(3));
				
				commMemberList.add(cm);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return commMemberList;
	}

	/** 가입 신청 회원 목록 조회 DAO
	 * @param conn
	 * @param communityNo
	 * @return commApplyList
	 * @throws Exception
	 */
	public List<CommunityApply> selectApllyMember(Connection conn, int communityNo) throws Exception {
		
		List<CommunityApply> commApplyList = new ArrayList<CommunityApply>();
		
		try {
			String sql = prop.getProperty("selectApplyMember");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CommunityApply coma = new CommunityApply();
				
				coma.setMemberNo(rs.getInt(1));
				coma.setCommunityName(rs.getString(2));
				coma.setMemberName(rs.getString(3));
				coma.setMemberGender(rs.getString(4));
				coma.setMemberBirth(rs.getString(5));
				coma.setMemberNickname(rs.getString(6));
				coma.setMemberProfileImage(rs.getString(7));
				
				commApplyList.add(coma);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return commApplyList;
	}

	/** 모임 삭제 DAO
	 * @param conn
	 * @param communityNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteCommunity(Connection conn, int communityNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteCommunity");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communityNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임 대표 이미지 변경 
	 * @param conn
	 * @param communityNo
	 * @param communityImage
	 * @return result
	 * @throws Exception
	 */
	public int updateCommunityImage(Connection conn, Community comm) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateCommunityImage");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comm.getCommunityImage());
			pstmt.setInt(2, comm.getCommunityNo());
			
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
	public int entrust(Connection conn, int communityNo, int memberNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("entrust");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임 가입
    * @param conn
    * @param memberNo
    * @param communityNo
    * @return result
    * @throws Exception
    */
   public int communityJoin(Connection conn, int memberNo, int communityNo) throws Exception {
      int result = 0;
      
      try {
         String sql = prop.getProperty("communityJoin");
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, memberNo);
         pstmt.setInt(2, communityNo);
         
         result = pstmt.executeUpdate();
         
      } finally {
         close(pstmt);
      }
      return result;
   }

   /** 모임 탈퇴
    * @param conn
    * @param memberNo
    * @param communityNo
    * @return result
    * @throws Exception
    */
   public int communitySecession(Connection conn, int memberNo, int communityNo) throws Exception {
      int result = 0;
      
      try {
         String sql = prop.getProperty("communitySecession");
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, memberNo);
         
         result = pstmt.executeUpdate();
         
      } finally {
         close(pstmt);
      }
      return result;
   }

	   /** 모임 이름 조회
	 * @param conn
	 * @param comm
	 * @return result
	 * @throws Exception
	 */
	public Community selectCommunityName(Connection conn, int communityNo) throws Exception {
		
		Community comm = null;
		
		try {
			String sql = prop.getProperty("selectCommunityName");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				comm = new Community();
				
				comm.setCommunityName(rs.getString(1));
				comm.setCommunityNo(rs.getInt(2));
			}
		} finally {
			close(rs);
			close(pstmt);
		}	
		return comm;
	}
}
