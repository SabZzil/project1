package org.sabzzil.service;

import java.util.List;

import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.ReplyVO;

public interface ReplyService {

	public List<ReplyVO> list(int bno, Criteria cri) throws Exception;
	
	public void add(ReplyVO replyVO) throws Exception;

	public void delete(int rno) throws Exception;
	
}
