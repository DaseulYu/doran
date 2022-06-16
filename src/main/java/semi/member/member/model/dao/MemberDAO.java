package semi.member.member.model.dao;

import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.member.member.model.vo.Member;



public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	public MemberDAO() {

		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/semi/member/sql/member-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member login(Connection conn, Member mem) throws Exception {

		Member loginMember = null;

		try {
			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				loginMember = new Member();

				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_ID"));
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setMemberGender(rs.getString("MEMBER_GENDER"));
				loginMember.setMemberBirth(rs.getString("MEMBER_BIRTH"));
				loginMember.setMemberPhone(rs.getString("MEMBER_PHONE"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setMemberLive(rs.getString("MEMBER_LIVE"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return loginMember;

	}

	/**
	 * 닉네임 중복검사 DAO
	 * 
	 * @param conn
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(Connection conn, String memberNickname) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("nicknameDupCheck");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(conn);
			close(pstmt);
		}
		return result;
	}

	/**
	 * 휴대폰 번호 중복 검사 DAO
	 * 
	 * @param conn
	 * @param memberPhone
	 * @return result
	 * @throws Exception
	 */
	public int phoneDupCheck(Connection conn, String memberPhone) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("phoneDupCheck");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberPhone);

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

	/** 회원 정보 수정 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member mem) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("updateMember");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberNickname());
			pstmt.setString(2, mem.getMemberPhone());
			pstmt.setInt(3, mem.getMemberNo());

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(Connection conn, String currentPw, String newPw, int memberNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("changePw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newPw);
			pstmt.setString(2, currentPw);
			pstmt.setInt(3, memberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/** 현재 비밀번호 확인 DAO
	 * @param conn
	 * @param currentPw
	 * @param loginMemberNo
	 * @return result
	 * @throws Exception
	 */
	public int currentPwConfirmCheck(Connection conn, String currentPw, int loginMemberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("currentPwConfirmCheck");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, currentPw);
			pstmt.setInt(2, loginMemberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/** 프로필 이미지 변경 DAO
	 * @param conn
	 * @param memberNo
	 * @param profileImage
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(Connection conn, int memberNo, String profileImage) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateProfileImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, profileImage);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 인증번호, 발급일 수정 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateCertification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 인증번호 생성 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertCertification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	


	/** 인증번호 확인 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 * @throws Exception
	 */
	public int checkNumber(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("checkNumber");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			pstmt.setString(3, inputEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 이메일 확인 DAO
	 * @param conn
	 * @param inputEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(Connection conn, String inputEmail) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("emailDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			close(pstmt);
		}
		return result;
	}


	/**비밀번호 재설정 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int findPw(Connection conn, Member mem) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("resetPw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberPw());
			pstmt.setString(2, mem.getMemberName());
			pstmt.setString(3, mem.getMemberEmail());
		
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo, String memberPw) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Member> myCommunityList(Connection conn, int memberNo) throws Exception{
		List<Member> commuList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("myCommunityList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				Member mem = new Member();
				mem.setCommunityName(rs.getString(1));
				mem.setCommunitArea(rs.getString(2));
				mem.setComunityFlag(rs.getString(3));
				mem.setApplyFlag(rs.getString(4));
				mem.setApplyDate(rs.getString(5));
				
				commuList.add(mem);				
				
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return commuList;
	}

}
