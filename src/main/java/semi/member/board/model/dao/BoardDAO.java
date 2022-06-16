package semi.member.board.model.dao;

import static semi.member.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.member.board.model.vo.Board;
import semi.member.board.model.vo.Pagination;
import semi.member.member.model.dao.MemberDAO;

public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	public BoardDAO() {

		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/semi/member/sql/board-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전체 게시글 수 조회 DAO
	 * 
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return listCount;
	}

	/**
	 * 게시판에서 일정한 범위의 목록 조회 DAO
	 * 
	 * @param conn
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, semi.member.board.model.vo.Pagination pagination)
			throws Exception {
		List<Board> boardList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectBoardList");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setCommunityNo(rs.getInt("COMMUNITY_NO"));
				board.setCommunityName(rs.getString("COMMUNITY_NM"));
				board.setCommunityImage(rs.getString("COMMUNITY_IMG"));
				board.setCommunityArea(rs.getString("COMMUNITY_AREA"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setCategoryNo(rs.getInt("CATEGORY_NO"));
				board.setCommunityMember(rs.getInt("COMMUNITY_MEMBER"));
				board.setPick(rs.getInt("PICK"));

				boardList.add(board);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}

	/**
	 * 조건을 만족하는 게시글 수 조회 DAO
	 * 
	 * @param conn
	 * @param query
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, String query) throws Exception {

		int listCount = 0;
		try {
			String sql = prop.getProperty("searchListCount") + " AND COMMUNITY_NM LIKE '%" + query + "%' ";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/**
	 * 조건을 만족하는 게시글 목록 조회 DAO
	 * 
	 * @param conn
	 * @param pagination
	 * @param query
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> serchBoardList(Connection conn, semi.member.board.model.vo.Pagination pagination, String query)
			throws Exception {
		List<Board> boardList = new ArrayList<Board>();
		try {

			String sql = prop.getProperty("searchBoardList1") + " AND COMMUNITY_NM LIKE '%" + query + "%' "
					+ prop.getProperty("searchBoardList2");
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setCommunityNo(rs.getInt("COMMUNITY_NO"));
				board.setCommunityName(rs.getString("COMMUNITY_NM"));
				board.setCommunityArea(rs.getString("COMMUNITY_AREA"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setCommunityMember(rs.getInt("COMMUNITY_MEMBER"));
				board.setPick(rs.getInt("PICK"));
				board.setCommunityImage(rs.getString("COMMUNITY_IMG"));

				boardList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/**
	 * 카테고리명 조회 DAO
	 * 
	 * @param conn
	 * @param type
	 * @return categoryName
	 * @throws Exception
	 */
	public String selectCategoryName(Connection conn, int type) throws Exception {
		String categoryName = null;
		try {
			String sql = prop.getProperty("selectCategoryName");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				categoryName = rs.getString(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return categoryName;
	}

	/**
	 * 카테고리별 게시글 수 조회 DAO
	 * 
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int getCategoryListCount(Connection conn, int type) throws Exception {
		int listCount = 0;
		try {

			String sql = prop.getProperty("getCategoryListCount");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/** 카테고리별 게시글 수 DAO
	 * @param conn
	 * @param query
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int searchCategoryListCount(Connection conn, String query, int type) throws Exception{
		int listCount = 0;
		try {
			String sql = prop.getProperty("SearchCategoryListCount")  + " AND COMMUNITY_NM LIKE '%" + query + "%' "  ;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/** 카테고리별 검색 목록 DAO
	 * @param conn
	 * @param pagination
	 * @param query
	 * @param type
	 * @return categoryList
	 * @throws Exception
	 */
	public List<Board> searchCategoryList(Connection conn, Pagination pagination,
			String query, int type) throws Exception{
		
		List<Board> categoryList = new ArrayList<Board>();
		try {

			String sql = prop.getProperty("searchCategoryList1") + " AND COMMUNITY_NM LIKE '%" + query + "%' "
					+ prop.getProperty("searchCategoryList2");
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setCommunityNo(rs.getInt("COMMUNITY_NO"));
				board.setCommunityName(rs.getString("COMMUNITY_NM"));
				board.setCommunityArea(rs.getString("COMMUNITY_AREA"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setCommunityMember(rs.getInt("COMMUNITY_MEMBER"));
				board.setPick(rs.getInt("PICK"));
				board.setCommunityImage(rs.getString("COMMUNITY_IMG"));

				categoryList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return categoryList;
	}

	public List<Board> selectCategoryList(Connection conn, Pagination pagination, int type) throws Exception{
		List<Board> categoryList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectCategoryList");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();

				board.setCommunityNo(rs.getInt("COMMUNITY_NO"));
				board.setCommunityName(rs.getString("COMMUNITY_NM"));
				board.setCommunityArea(rs.getString("COMMUNITY_AREA"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setCommunityMember(rs.getInt("COMMUNITY_MEMBER"));
				board.setPick(rs.getInt("PICK"));
				board.setCommunityImage(rs.getString("COMMUNITY_IMG"));
				categoryList.add(board);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return categoryList;
	}

	
	
}
