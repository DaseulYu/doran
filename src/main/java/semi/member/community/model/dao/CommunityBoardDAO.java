package semi.member.community.model.dao;

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
import semi.member.community.model.vo.BoardDetail;
import semi.member.community.model.vo.BoardImage;
import semi.member.community.model.vo.CommunityBoard;

public class CommunityBoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CommunityBoardDAO() {
		try {
			prop = new Properties();
			String filePath = CommunityBoardDAO.class.getResource("/semi/member/sql/communityBoard-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시판 종류 조회
	 * @param conn
	 * @param type
	 * @return boardName
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int type) throws Exception {
		String boardName = null;
		
		try {
			String sql = prop.getProperty("selectBoardName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardName = rs.getString(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardName;
	}

	/** 게시글 수 조회
	 * @param conn
	 * @param type
	 * @param communityNo 
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int type, int communityNo) throws Exception {
		int listCount = 0;
		try {
			String sql = prop.getProperty("getListCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, communityNo);
			
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

	
	/** 게시글 범위 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param communityNo 
	 * @param boardNo 
	 * @return boardList
	 * @throws Exception
	 */
	public List<CommunityBoard> communityBoardList(Connection conn, Pagination pagination, int type, int communityNo) throws Exception {

		List<CommunityBoard> boardList = new ArrayList<CommunityBoard>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, communityNo);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBoard list = new CommunityBoard();
				
				list.setBoardNo(rs.getInt("BOARD_NO"));
				list.setBoardName(rs.getString("BOARD_NM"));
				list.setBoardTitle(rs.getString("BOARD_TITLE"));
				list.setBoardContent(rs.getString("BOARD_CONTENT"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setProfileImage(rs.getString("PROFILE_IMG"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				list.setMemberNo(rs.getInt("MEMBER_NO"));
				list.setImage0(rs.getString("IMG0"));
				list.setImage1(rs.getString("IMG1"));

				boardList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	
	/** 자유게시판 상세 조회
	 * @param conn
	 * @param boardNo
	 * @param communityNo 
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(Connection conn, int boardNo, int communityNo) throws Exception {

		BoardDetail detail = null;
		
		try {
			String sql = prop.getProperty("selectBoardDetail");
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, communityNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				detail = new BoardDetail();
				
				detail.setBoardNo(rs.getInt(1));
				detail.setBoardTitle(rs.getString(2));
				detail.setBoardContent(rs.getString(3));
				detail.setCreateDate(rs.getString(4));
				detail.setReadCount(rs.getInt(5));
				detail.setMemberNickname(rs.getString(6));
				detail.setProfileImage(rs.getString(7));
				detail.setMemberNo(rs.getInt(8));
				detail.setBoardName(rs.getString(9));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return detail;
	}

	/** 이미지 리스트 조회
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<BoardImage> selectImageList(Connection conn, int boardNo) throws Exception {

		List<BoardImage> imageList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectImageList");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardImage image = new BoardImage();
				
				image.setImageNo(rs.getInt(1));
				image.setImageLevel(rs.getInt(2));
				image.setImageName(rs.getString(3));
				image.setImageOriginal(rs.getString(4));
				image.setBoardNo(rs.getInt(5));
				
				imageList.add(image);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return imageList;
	}

	
	/** 다음 게시글 번호 조회
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception {
		int boardNo = 0;
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return boardNo;
	}

	/** 자유 게시글 등록
	 * @param conn
	 * @param detail
	 * @param boardCode
	 * @param communityNo 
	 * @param memberNo 
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, BoardDetail detail, int boardCode, int communityNo, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, detail.getBoardNo());
			pstmt.setString(2, detail.getBoardTitle());
			pstmt.setString(3, detail.getBoardContent());
			pstmt.setInt(4, boardCode);
			pstmt.setInt(5, memberNo);
			pstmt.setInt(6, communityNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 이미지 삽입
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int insertBoardImage(Connection conn, BoardImage image) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, image.getImageLevel());
			pstmt.setString(2, image.getImageName());
			pstmt.setString(3, image.getImageOriginal());
			pstmt.setInt(4, image.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 자유게시글 수정
	 * @param conn
	 * @param detail
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, BoardDetail detail) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setInt(3, detail.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 자유게시글 이미지 수정
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardImage(Connection conn, BoardImage image) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, image.getImageName());
			pstmt.setString(2, image.getImageOriginal());
			pstmt.setInt(3, image.getBoardNo());
			pstmt.setInt(4, image.getImageLevel());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 이미지 삭제
	 * @param conn
	 * @param deleteList
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoardImage(Connection conn, String deleteList, int boardNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoardImage") + deleteList + ")";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 자유게시글 검색 게시글 수 조회
	 * @param conn
	 * @param type
	 * @param communityNo
	 * @param condition 
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, int type, int communityNo, String condition) throws Exception {
		int listCount = 0;
		try {
			String sql = prop.getProperty("searchListCount")+condition;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, communityNo);
			
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

	
	
	/** 자유게시글 검색 게시글 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param communityNo
	 * @param condition 
	 * @return boardList
	 * @throws Exception
	 */
	public List<CommunityBoard> searchBoardList(Connection conn, Pagination pagination, int type, int communityNo, String condition) throws Exception {
		List<CommunityBoard> boardList = new ArrayList<CommunityBoard>();
		
		try {
			String sql = prop.getProperty("searchBoardList1")+condition+prop.getProperty("searchBoardList2");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, type);
			pstmt.setInt(2, communityNo);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBoard list = new CommunityBoard();
				
				list.setBoardNo(rs.getInt("BOARD_NO"));
				list.setBoardName(rs.getString("BOARD_NM"));
				list.setBoardTitle(rs.getString("BOARD_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));
				
				boardList.add(list);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/** 자유 게시글 삭제
	 * @param conn
	 * @param communityNo
	 * @param boardNo
	 * @param communityNo 
	 * @param type 
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo, int communityNo, int type) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, communityNo);
			pstmt.setInt(3, type);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 모임 메인 자유게시판
	 * @param conn
	 * @param communityNo
	 * @return bList
	 * @throws Exception
	 */
	public List<CommunityBoard> selectBoardList(Connection conn, int communityNo) throws Exception {
		List<CommunityBoard> bList = new ArrayList<CommunityBoard>();
		
		try {
			String sql = prop.getProperty("selectBoardListMain");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBoard list = new CommunityBoard();
				
				list.setBoardNo(rs.getInt("BOARD_NO"));
				list.setBoardTitle(rs.getString("BOARD_TITLE"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setCreateDate(rs.getString("CREATE_DT"));
				list.setReadCount(rs.getInt("READ_COUNT"));

				bList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return bList;
	}

	/** 모임 메인 후기게시판
	 * @param conn
	 * @param communityNo
	 * @return rList
	 * @throws Exception
	 */
	public List<CommunityBoard> selectReviewListMain(Connection conn, int communityNo) throws Exception {
		List<CommunityBoard> rList = new ArrayList<CommunityBoard>();
		
		try {
			String sql = prop.getProperty("selectReviewListMain");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communityNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBoard list = new CommunityBoard();
				
				list.setBoardTitle(rs.getString("BOARD_TITLE"));
				list.setBoardContent(rs.getString("BOARD_CONTENT"));
				list.setProfileImage(rs.getString("PROFILE_IMG"));
				list.setMemberNickname(rs.getString("MEMBER_NICK"));
				list.setImage0(rs.getString("IMG_NM"));

				rList.add(list);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return rList;
	}

	



	
	
	

}
