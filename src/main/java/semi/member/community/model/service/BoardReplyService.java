package semi.member.community.model.service;

import static semi.member.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.member.common.Util;
import semi.member.community.model.dao.BoardReplyDAO;
import semi.member.community.model.vo.BoardReply;

public class BoardReplyService {
	
	private BoardReplyDAO dao = new BoardReplyDAO();

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<BoardReply> selectReplyList(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<BoardReply> rList = dao.selectReplyList(conn, boardNo);
		
		close(conn);
		
		return rList;
	}

	/** 댓글 등록
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(BoardReply reply) throws Exception {
		Connection conn = getConnection();
		
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		reply.setReplyContent(Util.newLineHandling( reply.getReplyContent()));
		
		int result = dao.insertReply(conn, reply);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 삭제
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception {
		Connection conn = getConnection();
		
		replyContent = Util.XSSHandling(replyContent);
		replyContent = Util.newLineHandling(replyContent);
		
		int result = dao.updateReply(conn, replyNo, replyContent);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	

}
