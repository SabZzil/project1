package org.sabzzil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sabzzil.domain.BoardVO;

public interface BoardMapper {

	@Select("select * from tbl_board where bno>0 "
			+ "order by bno desc limit #{start}, #{articlePerPage}")
	public List<BoardVO> list(@Param("start") int start, 
			@Param("articlePerPage") int articlePerPage) throws Exception;
	
	@Select("select count(bno) from tbl_board where bno>0")
	public int totalArticles() throws Exception;
	
	@Select("select * from tbl_board where bno=#{bno}")
	public BoardVO read(@Param("bno") int bno) throws Exception;
	
	@Update("update tbl_board set viewcnt = viewcnt + 1 where bno=#{bno}")
	public void incViewcnt(@Param("bno") int bno) throws Exception;
	
	@Insert("insert into tbl_board (title, content, writer) "
			+ "values (#{title}, #{content}, #{writer})")
	public void create(BoardVO boardVO) throws Exception;
	
	public BoardVO test(@Param("bno") int bno) throws Exception;
	
	public List<BoardVO> sList(@Param("start") int start, 
			@Param("articlePerPage") int articlePerPage,
			@Param("searchType") String searchType,
			@Param("keyword") String keyword) throws Exception;
	
	public int sTotalArticles(@Param("searchType") String searchType,
			@Param("keyword") String keyword) throws Exception;
}
