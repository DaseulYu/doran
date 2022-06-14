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

import semi.member.community.model.vo.SignupDetail;

public class SignupDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public SignupDAO() {
		try {
			prop = new Properties();
			String filePath = SignupDAO.class.getResource("/semi/member/sql/signup-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 가입 인사 목록 조회
	 * @param conn
	 * @param communityNo
	 * @return detail
	 * @throws Exception
	 */
	public List<SignupDetail> selectSignupList(Connection conn, int communityNo) throws Exception {

		List<SignupDetail> detail = new ArrayList<SignupDetail>();
		
		try {
			String sql = prop.getProperty("selectSignupList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SignupDetail d = new SignupDetail();
				
				d.setSignupNo(rs.getInt(1));
				d.setMemberNickname(rs.getString(2));
				d.setProfileImage(rs.getString(3));
				d.setSignupContent(rs.getString(4));
				d.setCreateDate(rs.getString(5));
				
				detail.add(d);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return detail;
	}
	
	
	

}
