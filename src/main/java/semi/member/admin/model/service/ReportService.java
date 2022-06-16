package semi.member.admin.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.member.admin.model.dao.ReportDAO;
import semi.member.admin.model.vo.NoticeList;
import semi.member.admin.model.vo.Pagination;
import semi.member.admin.model.vo.Report;

public class ReportService {

	private ReportDAO dao = new ReportDAO();
	
	
	/** 신고 
	 * @param communityNo
	 * @param boardNo
	 * @param memberNo
	 * @param reportTitle 
	 * @return result
	 * @throws Exception
	 */
	public int insertReport(int communityNo, int boardNo, int memberNo, String reportTitle) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertReport(conn, communityNo, boardNo, memberNo,  reportTitle);
		
		if(result > 0) commit(conn);
		else rollback(conn);
	
		return result;
	}


	/** 신고 목록 조회
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> reportListAll(int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.getReportListCountAll(conn);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Report> reportList = dao.reportListAll(conn, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("reportList", reportList);
		
		close(conn);
		
		return map;
	}

}
