package org.sabzzil.service;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.SearchCriteria;

public interface BoardService {

	public List<BoardVO> list(Criteria cri) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void create(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> sList(SearchCriteria cri) throws Exception;
	
	public List<String> getAttach(int bno) throws Exception;
	
	public void modify(BoardVO boardVO) throws Exception;
}
