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

import semi.member.community.model.vo.BoardSignup;

public class BoardSignupDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardSignupDAO() {
		try {
			prop = new Properties();
			String filePath = BoardReplyDAO.class.getResource("/semi/member/sql/boardReply-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 가입 인사 목록 조회
	 * @param conn
	 * @param communityNo
	 * @return sList
	 * @throws Exception
	 */
	public List<BoardSignup> selectSignupList(Connection conn, int communityNo) throws Exception {

		List<BoardSignup> sList = new ArrayList<BoardSignup>();
		
		try {
			String sql = prop.getProperty("selectSignupList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardSignup b = new BoardSignup();
				
				b.setSignupNo(rs.getInt(1));
				b.setMemberNickname(rs.getString(2));
				b.setProfileImage(rs.getString(3));
				b.setSignupContent(rs.getString(4));
				b.setCreateDate(rs.getString(5));
				b.setMemberNo(rs.getInt(6));
				
				sList.add(b);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return sList;
	}

	/** 가입 인사 등록
	 * @param conn
	 * @param signup
	 * @return result
	 * @throws Exception
	 */
	public int insertSignup(Connection conn, BoardSignup signup) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertSignup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, signup.getCommunityNo());
			pstmt.setString(2, signup.getSignupContent());
			pstmt.setInt(3, signup.getMemberNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 가입 인사 삭제
	 * @param conn
	 * @param signupNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteSignup(Connection conn, int signupNo) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteSignup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, signupNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 가입 인사 수정
	 * @param conn
	 * @param signupNo 
	 * @param signupContent
	 * @return result
	 * @throws Exception
	 */
	public int updateSignup(Connection conn, int signupNo, String signupContent) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("updateSignup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, signupContent);
			pstmt.setInt(2, signupNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}


	/** 모임 메인 가입인사
	 * @param conn
	 * @param communityNo
	 * @return sList
	 * @throws Exception
	 */
	public List<BoardSignup> selectSignupListMain(Connection conn, int communityNo) throws Exception {
		List<BoardSignup> sList = new ArrayList<BoardSignup>();
		
		try {
			String sql = prop.getProperty("selectSignupListMain");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardSignup b = new BoardSignup();
				
				b.setMemberNo(rs.getInt("SIGNUP_NO"));
				b.setMemberNickname(rs.getString("MEMBER_NICK"));
				b.setProfileImage(rs.getString("PROFILE_IMG"));
				b.setSignupContent(rs.getString("SIGNUP_CONTENT"));
				b.setCreateDate(rs.getString("CREATE_DT"));
				
				sList.add(b);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return sList;
	}
	
	
	
	
}
