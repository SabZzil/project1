package org.sabzzil.persistence;

import java.util.List;

import org.sabzzil.domain.ReplyVO;

public interface ReplyDao {

	public List<ReplyVO> list(int bno, int start, int replyPerPage) throws Exception;
	
	public int totalReplies(int bno) throws Exception;
	
	public void add(ReplyVO replyVO) throws Exception;
	
	public void incReplycnt(int bno) throws Exception;
	
	public void delete(int rno) throws Exception;
	
	public void decReplycnt(int rno) throws Exception;
}
