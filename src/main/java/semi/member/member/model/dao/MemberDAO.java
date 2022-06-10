package semi.member.member.model.dao;

import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
			String filePath = MemberDAO.class.getResource("semi/member/sql/member-sql.xml").getPath();
			
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
			
			if(rs.next()) {
				loginMember = new Member();
				
				
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_ID"));
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
//				loginMember.setMemberGender(rs.getString("MEMBER_GENDER").charAt(0));
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
	
}


