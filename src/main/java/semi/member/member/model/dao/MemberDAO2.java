package semi.member.member.model.dao;

import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.member.member.model.vo.Member;

public class MemberDAO2 {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	public MemberDAO2() {

		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/semi/member/sql/memberSignUp-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 회원가입 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member mem) throws Exception{
		
		
		int result = 0; 

		try {
			String sql = prop.getProperty("signUp");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberName());
			pstmt.setString(4, mem.getMemberGender());
			pstmt.setString(5, mem.getMemberBirth());
			pstmt.setString(6, mem.getMemberPhone());
			pstmt.setString(7, mem.getMemberNickname());
			pstmt.setString(8, mem.getMemberLive());
			
			
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}
}
