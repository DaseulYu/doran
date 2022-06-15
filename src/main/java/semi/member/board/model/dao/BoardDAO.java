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

import semi.member.admin.model.vo.Pagination;
import semi.member.board.model.vo.Board;
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


	/** 게시판에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, semi.member.board.model.vo.Pagination pagination) throws Exception{
		List<Board> boardList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectBoardList");

			// BETWEEN 구문에 들어갈 범위 계산
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

				boardList.add(board);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}

	/** 조건을 만족하는 게시글 수 조회 DAO
	 * @param conn
	 * @param query 
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, String query) throws Exception{
		
		int listCount = 0;
		try {
			String sql = prop.getProperty("searchListCount") + " AND COMMUNITY_NM LIKE '%"+query+"%' ";
			
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

	/** 조건을 만족하는 게시글 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param query
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> serchBoardList(Connection conn, semi.member.board.model.vo.Pagination pagination, String query) throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		try {
			
			String sql = prop.getProperty("searchBoardList1")
					+ " AND COMMUNITY_NM LIKE '%"+query+"%' "
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

				boardList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
}
