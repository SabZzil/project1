package org.sabzzil.service;

import java.util.List;

import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.ReplyVO;
import org.sabzzil.persistence.ReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao dao;
	
	@Transactional
	@Override
	public List<ReplyVO> list(int bno, Criteria cri) throws Exception {
		cri.setTotalReplies(dao.totalReplies(bno));
		
		return dao.list(bno, cri.getReplyStart(), cri.getReplyPerPage());
	}
	
	@Transactional
	@Override
	public void add(ReplyVO replyVO) throws Exception {
		dao.add(replyVO);
		dao.incReplycnt(replyVO.getBno());
	}
	
	@Transactional
	@Override
	public void delete(int rno) throws Exception {
		dao.decReplycnt(rno);
		dao.delete(rno);
	}
}
