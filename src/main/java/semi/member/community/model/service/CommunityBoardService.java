package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.member.admin.model.vo.Pagination;
import semi.member.common.Util;
import semi.member.community.model.dao.CommunityBoardDAO;
import semi.member.community.model.vo.BoardDetail;
import semi.member.community.model.vo.BoardImage;
import semi.member.community.model.vo.CommunityBoard;

public class CommunityBoardService {

	private CommunityBoardDAO dao = new  CommunityBoardDAO();

	/** 카테고리별 게시글 조회
	 * @param cp
	 * @param type
	 * @param boardNo 
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> communityBoardList(int cp, int type, int communityNo) throws Exception {
		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type);
		
		int listCount = dao.getListCount(conn, type, communityNo);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<CommunityBoard> boardList = dao.communityBoardList(conn, pagination, type, communityNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
	}

	/** 자유게시판 상세 조회
	 * @param boardNo
	 * @param communityNo 
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(int boardNo, int communityNo) throws Exception {
		Connection conn = getConnection();
		
		BoardDetail detail = dao.selectBoardDetail(conn, boardNo, communityNo);
		
		if(detail != null) {
			List<BoardImage> imageList = dao.selectImageList(conn, boardNo);
			detail.setImageList(imageList);
		}
		close(conn);
		
		return detail;
	}

	
	/** 게시글 등록
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @param communityNo 
	 * @param memberNo 
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardDetail detail, List<BoardImage> imageList, int boardCode, int communityNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		detail.setBoardNo(boardNo);
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		int result = dao.insertBoard(conn, detail, boardCode, communityNo, memberNo);
		
		if(result > 0) {
			for(BoardImage image : imageList) {
				image.setBoardNo(boardNo);
				
				result = dao.insertBoardImage(conn, image);
				
				if(result == 0) { 
					break;
				}
			}
		}
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
			boardNo = 0;
		}
		close(conn);
		
		return boardNo;
	}

	/** 자유게시글 수정
	 * @param imageList
	 * @param detail
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(List<BoardImage> imageList, BoardDetail detail, String deleteList) throws Exception {
		Connection conn = getConnection();
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		int result = dao.updateBoard(conn, detail);
		
		if(result > 0){
			
			for(BoardImage image : imageList){
				image.setBoardNo(detail.getBoardNo());
				result = dao.updateBoardImage(conn, image);
				
				if(result == 0) {
					result = dao.insertBoardImage(conn, image);
				}
			}
			if(!deleteList.equals("")) {
				result = dao.deleteBoardImage(conn, deleteList, detail.getBoardNo());
			}
		}
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 자유게시판 검색
	 * @param type
	 * @param cp
	 * @param communityNo
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchBoardList(int cp, int type, int communityNo, String key, String query) throws Exception {
		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type);
		
		String condition = null;
		
		switch(key) {
		case "t" : condition = " AND BOARD_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') "; break;
		case "w" : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
		}
		
		int listCount = dao.searchListCount(conn, type, communityNo, condition);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<CommunityBoard> boardList = dao.searchBoardList(conn, pagination, type, communityNo, condition);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
	}

	/** 자유 게시글 삭제
	 * @param communityNo
	 * @param boardNo
	 * @param communityNo 
	 * @param type 
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo, int communityNo, int type) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo, communityNo, type);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 모임 메인 자유게시판
	 * @param communityNo
	 * @return bList
	 * @throws Exception
	 */
	public List<CommunityBoard> selectBoardList(int communityNo) throws Exception {
		Connection conn = getConnection();

		List<CommunityBoard> bList = dao.selectBoardList(conn, communityNo);
		
		close(conn);
		
		return bList;
	}

	/** 모임 메인 후기게시판
	 * @param communityNo
	 * @return rList
	 * @throws Exception
	 */
	public List<CommunityBoard> selectReviewListMain(int communityNo) throws Exception {
		Connection conn = getConnection();

		List<CommunityBoard> rList = dao.selectReviewListMain(conn, communityNo);
		
		close(conn);
		
		return rList;
	}



	
}
