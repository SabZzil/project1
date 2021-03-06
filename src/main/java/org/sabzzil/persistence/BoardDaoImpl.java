package org.sabzzil.persistence;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list(int start, int articlePerPage) throws Exception {
		return mapper.list(start, articlePerPage);
	}
	
	@Override
	public int totalArticles() throws Exception {
		return mapper.totalArticles();
	}
	
	@Override
	public List<BoardVO> sList(int start, int articlePerPage,
			String searchType, String keyword) throws Exception {
		return mapper.sList(start, articlePerPage, searchType, keyword);
	}
	
	@Override
	public int sTotalArticles(String searchType, String keyword) throws Exception {
		return mapper.sTotalArticles(searchType, keyword);
	}
	
	@Override
	public BoardVO read(int bno) throws Exception {
		return mapper.read(bno);
	}
	
	@Override
	public void incViewcnt(int bno) throws Exception {
		mapper.incViewcnt(bno);
	}
	
	@Override
	public void create(BoardVO boardVO) throws Exception {
		mapper.create(boardVO);
	}
	
	@Override
	public void addAttach(String fullName) throws Exception {
		mapper.addAttach(fullName);
	}
	
	@Override
	public List<String> getAttach(int bno) throws Exception {
		return mapper.getAttach(bno);
	}
	
	@Override
	public void modify(BoardVO boardVO) throws Exception {
		mapper.modify(boardVO);
	}
	
	@Override
	public void removeAttach(int bno) throws Exception {
		mapper.removeAttach(bno);
	}
	
	@Override
	public void replaceAttach(String[] files, int bno) throws Exception {
		for(String fullName : files) {
			mapper.replaceAttach(fullName, bno);
		}
	}
}
