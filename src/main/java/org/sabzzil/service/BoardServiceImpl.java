package org.sabzzil.service;

import java.util.List;

import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.Criteria;
import org.sabzzil.domain.SearchCriteria;
import org.sabzzil.persistence.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
		cri.setTotalArticles(dao.totalArticles());
		return dao.list(cri.getStart(), cri.getArticlePerPage());
	}
	
	@Override
	public List<BoardVO> sList(SearchCriteria cri) throws Exception {
		cri.setTotalArticles(dao.sTotalArticles(cri.getSearchType(), cri.getKeyword()));
		return dao.sList(cri.getStart(), cri.getArticlePerPage(), 
				cri.getSearchType(), cri.getKeyword());
	}
	
	@Transactional
	@Override
	public BoardVO read(int bno) throws Exception {
		dao.incViewcnt(bno);
		return dao.read(bno);
	}
	
	@Transactional
	@Override
	public void create(BoardVO boardVO) throws Exception {
		dao.create(boardVO);
		String[] files = boardVO.getFiles();
		
		if(files==null) {
			return;
		}
		
		for(String fileName : files) {
			dao.addAttach(fileName);
		}
	}
}
