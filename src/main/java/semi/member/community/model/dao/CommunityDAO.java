package semi.member.community.model.dao;


import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.member.community.model.vo.Community;

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
	
	
	

}
