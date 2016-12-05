package org.sabzzil.persistence;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;

public interface BoardDao {

	public List<BoardVO> list(int start, int articlePerPage) throws Exception;
	
	public int totalArticles() throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void incViewcnt(int bno) throws Exception;
	
	public void create(BoardVO boardVO) throws Exception;
}