package org.sabzzil.service;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;

public interface BoardService {

	public List<BoardVO> list(Criteria cri) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void create(BoardVO boardVO) throws Exception;
}
