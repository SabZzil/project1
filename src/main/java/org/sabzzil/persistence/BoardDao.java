package org.sabzzil.persistence;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;

public interface BoardDao {

	public List<BoardVO> list(int start, int articlePerPage) throws Exception;
	
	public int totalArticles() throws Exception;
	
	public List<BoardVO> sList(int start, int articlePerPage,
			String searchType, String keyword) throws Exception;
	
	public int sTotalArticles(String searchType, String keyword) throws Exception;	
	
	public BoardVO read(int bno) throws Exception;
	
	public void incViewcnt(int bno) throws Exception;
	
	public void create(BoardVO boardVO) throws Exception;
	
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(int bno) throws Exception;
	
	public void modify(BoardVO boardVO) throws Exception;
	
	public void removeAttach(int bno) throws Exception;
	
	public void replaceAttach(String[] files, int bno) throws Exception;
}
