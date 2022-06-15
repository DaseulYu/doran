package semi.member.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static semi.member.common.JDBCTemplate.*;

import semi.member.board.model.dao.BoardDAO;
import semi.member.board.model.vo.Board;
import semi.member.board.model.vo.Pagination;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	/**
	 * 게시글 조회
	 * 
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int cp) throws Exception {
		Connection conn = getConnection();

		// 2-1. 특정 게시판 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(conn);

		// 2-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);

		// 3. 게시글 목록 조회
		List<Board> boardList = dao.selectBoardList(conn, pagination);

		// 4. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("boardList", boardList);

		close(conn);

		return map;
	}

	/**
	 * 검색 목록 조회 Service
	 * 
	 * @param cp
	 * @param key
	 * @param query
	 * @return result
	 * @throws Exception
	 */
	public Map<String, Object> serchBoardList(int cp, String key, String query) throws Exception {

		Connection conn = getConnection();

		int listCount = dao.searchListCount(conn, query);

		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = dao.serchBoardList(conn, pagination, query);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		return map;
	}
}
