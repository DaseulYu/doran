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

	/**
	 * 인증번호 생성 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int insertCertification(Connection conn, String memberId, String cNumber) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("insertCertification");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);
			pstmt.setString(2, cNumber);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 인증번호 확인 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int checkNumber(Connection conn, String memberId, String cNumber) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("checkNumber");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);
			pstmt.setString(2, cNumber);
			pstmt.setString(3, memberId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 인증번호, 발급일 수정 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int updateCertification(Connection conn, String memberId, String cNumber) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("updateCertification");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, cNumber);
			pstmt.setString(2, memberId);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/** 이메일 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception{
		int result = 0; // 결과 저장용 변수

		try {
			String sql = prop.getProperty("idDupCheck");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}
}
