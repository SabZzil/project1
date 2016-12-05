package org.sabzzil.persistence;

import java.util.List;

import org.sabzzil.domain.ReplyVO;
import org.sabzzil.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDaoImpl implements ReplyDao{

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> list(int bno, int start, int replyPerPage) throws Exception {
		return mapper.list(bno, start, replyPerPage);
	}
	
	@Override
	public int totalReplies(int bno) throws Exception {
		return mapper.totalReplies(bno);
	}
	
	@Override
	public void add(ReplyVO replyVO) throws Exception {
		mapper.add(replyVO);
	}
	
	@Override
	public void incReplycnt(int bno) throws Exception {
		mapper.incReplycnt(bno);
	}
	
	@Override
	public void delete(int rno) throws Exception {
		mapper.delete(rno);
	}
	
	@Override
	public void decReplycnt(int rno) throws Exception {
		mapper.decReplycnt(rno);
	}
}
