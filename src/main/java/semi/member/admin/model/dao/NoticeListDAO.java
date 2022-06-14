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

import semi.member.admin.model.vo.NoticeDetail;
import semi.member.admin.model.vo.NoticeImage;
import semi.member.admin.model.vo.NoticeList;
import semi.member.admin.model.vo.Pagination;

public class NoticeListDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public NoticeListDAO() {
		
		try {
			prop = new Properties();
			String filePath = NoticeListDAO.class.getResource("/semi/member/sql/admin-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 공지사항 카테고리 이름 조회
	 * @param conn
	 * @param type
	 * @return noticeName
	 * @throws Exception
	 */
	public String selectNoticeName(Connection conn, int type) throws Exception {
		
		String noticeName = null;
		
		try {
			String sql = prop.getProperty("selectNoticeName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeName = rs.getString(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeName;
	}

	
	/** 공지사항 카테고리별 전체 게시글 수 조회
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int type) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	
	/** 공지사항 카테고리별 범위 목록 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> selectNoticeList(Connection conn, Pagination pagination, int type) throws Exception {
		
		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("selectNoticeList");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				
				noticeList.add(list);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return noticeList;
	}

	
	/** 공지사항 전체 게시글 수 조회
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCountAll(Connection conn) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCountAll")+" WHERE NOTICE_ST = 'N'";
			
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

	
	/** 공지사항 게시글 전체 조회
	 * @param conn
	 * @param pagination
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> noticeListAll(Connection conn, Pagination pagination) throws Exception {
		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("noticeListAll1")+" WHERE NOTICE_ST = 'N' "+prop.getProperty("noticeListAll2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				
				noticeList.add(list);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	
	/** 관리자 게시글 수 전체 조회
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int adminGetListCountAll(Connection conn) throws Exception  {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCountAll");
			
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

	
	/** 관리자 게시글 전체 조회
	 * @param conn
	 * @param pagination
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> adminNoticeListAll(Connection conn, Pagination pagination) throws Exception {
		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("noticeListAll1")+prop.getProperty("noticeListAll2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				list.setNoticeState(rs.getString("NOTICE_ST").charAt(0));
				
				noticeList.add(list);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}


	/** 공지 게시글 상세 조회
	 * @param conn
	 * @param noticeNo
	 * @return detail
	 * @throws Exception
	 */
	public NoticeDetail selectNoticeDetail(Connection conn, int noticeNo) throws Exception {
		
		NoticeDetail detail = null;
		
		try {
			String sql = prop.getProperty("selectNoticeDetail");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				detail = new NoticeDetail();
				
				detail.setNoticeNo(rs.getInt(1));
				detail.setNoticeTitle(rs.getString(2));
				detail.setMemberNickname(rs.getString(3));
				detail.setNoticeContent(rs.getString(4));
				detail.setCreateDate(rs.getString(5));
				detail.setReadCount(rs.getInt(6));
				detail.setNoticeName(rs.getString(7));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return detail;
	}

	
	/** 첨부된 이미지 리스트 조회
	 * @param conn
	 * @param noticeNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<NoticeImage> selectImageList(Connection conn, int noticeNo) throws Exception {
		
		List<NoticeImage> imageList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectImageList");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeImage image = new NoticeImage();
				
				image.setImageNo(rs.getInt(1));
				image.setImageLevel(rs.getInt(2));
				image.setImageName(rs.getString(3));
				image.setImageOriginal(rs.getString(4));
				image.setNoticeNo(rs.getInt(5));
				
				imageList.add(image);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return imageList;
	}


	/** 다음 공지 게시글 번호 조회
	 * @param conn
	 * @return noticeNo
	 * @throws Exception
	 */
	public int nextNoticeNo(Connection conn) throws Exception {
		
		int noticeNo = 0;
		
		try {
			String sql = prop.getProperty("nextNoticeNo");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				noticeNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return noticeNo;
	}

	/** 공지 게시글 등록
	 * @param conn
	 * @param detail
	 * @param noticeCode
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Connection conn, NoticeDetail detail, int noticeCode) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertNotice");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, detail.getNoticeNo());
			pstmt.setInt(2, noticeCode);
			pstmt.setString(3, detail.getNoticeTitle());
			pstmt.setString(4, detail.getNoticeContent());
			pstmt.setInt(5, detail.getMemberNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 공지 게시글 이미지 등록
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int insertNoticeImage(Connection conn, NoticeImage image) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertNoticeImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, image.getImageLevel());
			pstmt.setString(2, image.getImageName());
			pstmt.setString(3, image.getImageOriginal());
			pstmt.setInt(4, image.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 공지 게시글 내용 수정
	 * @param conn
	 * @param detail
	 * @param noticeCode 
	 * @return result
	 * @throws Exception
	 */
	public int updateNotice(Connection conn, NoticeDetail detail, int noticeCode) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateNotice");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeCode);
			pstmt.setString(2, detail.getNoticeTitle());
			pstmt.setString(3, detail.getNoticeContent());
			pstmt.setInt(4, detail.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 공지 게시글 이미지 수정
	 * @param conn
	 * @param img
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeImage(Connection conn, NoticeImage img) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateNoticeImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getImageName());
			pstmt.setString(2, img.getImageOriginal());
			pstmt.setInt(3, img.getImageLevel());
			pstmt.setInt(4, img.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 공지 게시글 이미지 삭제
	 * @param conn
	 * @param deleteList
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteNoticeImage(Connection conn, String deleteList, int noticeNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteNoticeImage") + deleteList + ")";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 공지 게시글 삭제
	 * @param conn
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteNotice(Connection conn, int noticeNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteNotice");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 관리자 검색 결과 게시글 수 조회
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchAdminNoticeListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCountAll")+condition;
			
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

	/** 관리자 검색 결과 게시글 조회
	 * @param conn
	 * @param pagination
	 * @param condition
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> searchAdminNoticeList(Connection conn, Pagination pagination, String condition) throws Exception {
		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("noticeListAll1")+condition+prop.getProperty("noticeListAll2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				list.setNoticeState(rs.getString("NOTICE_ST").charAt(0));
				
				noticeList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	/** 카테고리별 검색 결과 게시글 수 조회
	 * @param conn
	 * @param type
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchNoticeListCount(Connection conn, int type, String condition) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCount")+condition;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return listCount;
	}

	/** 카테고리별 검색 결과 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param condition
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> searchNoticeList(Connection conn, Pagination pagination, int type, String condition) throws Exception {

		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("searchNoticeList1")+condition+prop.getProperty("searchNoticeList2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				
				noticeList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}
 
	/** 사용자 전체 게시글 검색 결과 수 조회
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int searchNoticeListAllCount(Connection conn, String condition) throws Exception {
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCountAll")+condition;
			
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

	/** 사용자 전체 게시글 검색 결과 목록 조회
	 * @param conn
	 * @param pagination
	 * @param condition
	 * @return noticeList
	 * @throws Exception
	 */
	public List<NoticeList> searchNoticeListAll(Connection conn, Pagination pagination, String condition) throws Exception {
		List<NoticeList> noticeList = new ArrayList<NoticeList>();
		
		try {
			String sql = prop.getProperty("noticeListAll1")+condition+prop.getProperty("noticeListAll2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeList list = new NoticeList();
				
				list.setNoticeNo(rs.getInt("NOTICE_NO"));
				list.setNoticeName(rs.getString("NOTICE_NM"));
				list.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				list.setNoticeState(rs.getString("NOTICE_ST").charAt(0));
				
				noticeList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	/** 공지 게시글 등록
	 * @param conn
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice2(Connection conn, int noticeNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertNotice2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

}
