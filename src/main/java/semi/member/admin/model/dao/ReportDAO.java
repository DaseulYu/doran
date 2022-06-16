package semi.member.admin.model.dao;

import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.member.admin.model.vo.Pagination;
import semi.member.admin.model.vo.Report;
import semi.member.community.model.dao.CommunityDAO;

public class ReportDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public ReportDAO() {
		try {
			prop = new Properties();
			String filePath = ReportDAO.class.getResource("/semi/member/sql/admin-sql.xml").getPath();
			
			prop.loadFromXML( new FileInputStream(filePath) );
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 신고
	 * @param conn
	 * @param communityNo
	 * @param boardNo
	 * @param memberNo
	 * @param reportTitle
	 * @return result
	 * @throws Exception
	 */
	public int insertReport(Connection conn, int communityNo, int boardNo, int memberNo, String reportTitle) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertReport");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, reportTitle);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, boardNo);
			pstmt.setInt(4, communityNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 신고 목록 조회
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportListCountAll(Connection conn) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getReportListCountAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return listCount;
	}

	/** 신고 목록 조회
	 * @param conn
	 * @param pagination
	 * @return reportList
	 * @throws Exception
	 */
	public List<Report> reportListAll(Connection conn, Pagination pagination) throws Exception {
		List<Report> reportList = new ArrayList<Report>();
		
		try {
			String sql = prop.getProperty("reportListAll");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Report report = new Report();
				
				report.setReportNo(rs.getInt("REPORT_NO"));
				report.setReportDate(rs.getString("REPORT_DATE"));
				report.setReportTitle(rs.getString("REPORT_TITLE"));
				report.setMemberID(rs.getString("MEMBER_ID"));
				report.setBoardTitle(rs.getString("BOARD_TITLE"));
				
				reportList.add(report);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return reportList;
	}
	
	



}
