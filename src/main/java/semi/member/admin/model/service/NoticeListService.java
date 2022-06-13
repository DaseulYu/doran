package semi.member.admin.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.member.admin.model.dao.NoticeListDAO;
import semi.member.admin.model.vo.NoticeDetail;
import semi.member.admin.model.vo.NoticeImage;
import semi.member.admin.model.vo.NoticeList;
import semi.member.admin.model.vo.Pagination;
import semi.member.common.Util;

public class NoticeListService {

	private NoticeListDAO dao = new NoticeListDAO();

	/** 공지사항 카테고리별 게시글 조회
	 * @param type
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectNoticeList(int type, int cp) throws Exception {
		
		Connection conn = getConnection();
		
		String noticeName = dao.selectNoticeName(conn, type);
		
		int listCount = dao.getListCount(conn, type);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.selectNoticeList(conn, pagination, type);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("noticeName", noticeName);
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}

	
	/** 공지사항 게시글 전체 조회
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> noticeListAll(int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.getListCountAll(conn);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.noticeListAll(conn, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}


	/** 관리자 게시글 전체 조회
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> adminNoticeListAll(int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.adminGetListCountAll(conn);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.adminNoticeListAll(conn, pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}


	/** 공지 게시글 상세 조회
	 * @param noticeNo
	 * @return detail
	 * @throws Exception
	 */
	public NoticeDetail selectNoticeDetail(int noticeNo) throws Exception {
		
		Connection conn = getConnection();
		
		NoticeDetail detail = dao.selectNoticeDetail(conn, noticeNo);
		
		if(detail != null) {
			List<NoticeImage> imageList = dao.selectImageList(conn, noticeNo);
			
			detail.setImageList(imageList);
		}
		close(conn);
		
		return detail;
	}


	/** 공지 게시글 등록
	 * @param detail
	 * @param imageList
	 * @param noticeCode
	 * @return noticeNo
	 * @throws Exception
	 */
	public int insertNotice(NoticeDetail detail, List<NoticeImage> imageList, int noticeCode) throws Exception {
		Connection conn = getConnection();
		
		int noticeNo = dao.nextNoticeNo(conn);
		
		detail.setNoticeNo(noticeNo);
		
//		XSS, 개행문자 처리
		detail.setNoticeTitle(Util.XSSHandling(detail.getNoticeTitle()));
		detail.setNoticeContent(Util.XSSHandling(detail.getNoticeContent()));
		detail.setNoticeContent(Util.newLineHandling(detail.getNoticeContent()));
		
		int result = dao.insertNotice(conn, detail, noticeCode);
		
		if(result > 0) {
			
			for(NoticeImage image : imageList) {
				image.setNoticeNo(noticeNo);
				
				result = dao.insertNoticeImage(conn, image);
				
				if(result == 0) { 
					break;
				}
			}
		}
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
			noticeNo = 0;
		}
		close(conn);
		
		return noticeNo;
	}


	/** 공지 게시글 수정
	 * @param imageList
	 * @param detail
	 * @param deleteList
	 * @param noticeCode 
	 * @return result
	 * @throws Exception
	 */
	public int updateNotice(List<NoticeImage> imageList, NoticeDetail detail, String deleteList, int noticeCode) throws Exception {
		Connection conn = getConnection();
		
//		XSS, 개행문자 처리
		detail.setNoticeTitle(Util.XSSHandling(detail.getNoticeTitle()));
		detail.setNoticeContent(Util.XSSHandling(detail.getNoticeContent()));
		detail.setNoticeContent(Util.newLineHandling(detail.getNoticeContent()));
		
		int result = dao.updateNotice(conn, detail, noticeCode);
		
//		이미지 수정
		if(result > 0) {
			for(NoticeImage img : imageList) {
				img.setNoticeNo(detail.getNoticeNo());
				result = dao.updateNoticeImage(conn, img);
				
				if(result == 0) {
					result = dao.insertNoticeImage(conn, img);
				}
			}
			
			// 이미지 삭제
			if(!deleteList.equals("")) {
				result = dao.deleteNoticeImage(conn, deleteList, detail.getNoticeNo());
			}
		}
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 공지 게시글 삭제
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteNotice(int noticeNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteNotice(conn, noticeNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 관리자 공지 게시글 검색
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchAdminNoticeList(int cp, String key, String query) throws Exception {
		Connection conn = getConnection();
		
		String condition = null;
		
		switch(key) {
		case "t" : condition = " WHERE NOTICE_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " WHERE NOTICE_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " WHERE (NOTICE_TITLE LIKE '%"+query+"%' OR NOTICE_CONTENT LIKE '%"+query+"%') "; break;
		}
		
		int listCount = dao.searchAdminNoticeListCount(conn, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.searchAdminNoticeList(conn, pagination, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}


	/** 사용자 공지 게시글 카테고리별 검색
	 * @param type
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchNoticeList(int type, int cp, String key, String query) throws Exception {
		Connection conn = getConnection();
		
		String noticeName = dao.selectNoticeName(conn, type);
		
		String condition = null;
		
		switch(key) {
		case "t" : condition = " AND NOTICE_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " AND NOTICE_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " AND (NOTICE_TITLE LIKE '%"+query+"%' OR NOTICE_CONTENT LIKE '%"+query+"%') "; break;
		}
		
		int listCount = dao.searchNoticeListCount(conn, type, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.searchNoticeList(conn, pagination, type, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("noticeName", noticeName);
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}


	/** 사용자 공지 게시글 전체 검색
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchNoticeListAll(int cp, String key, String query) throws Exception {
		Connection conn = getConnection();
		
		String condition = null;
		
		switch(key) {
		case "t" : condition = " WHERE NOTICE_ST = 'N' AND NOTICE_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " WHERE NOTICE_ST = 'N' AND NOTICE_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " WHERE NOTICE_ST = 'N' AND (NOTICE_TITLE LIKE '%"+query+"%' OR NOTICE_CONTENT LIKE '%"+query+"%') "; break;
		}
		
		int listCount = dao.searchNoticeListAllCount(conn, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<NoticeList> noticeList = dao.searchNoticeListAll(conn, pagination, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		close(conn);
		
		return map;
	}


	/** 공지 게시글 등록
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice2(int noticeNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertNotice2(conn, noticeNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}





}
