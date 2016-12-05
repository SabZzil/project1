package org.sabzzil.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.ReplyVO;

public interface ReplyMapper {

	@Select("select * from tbl_reply where bno=#{bno} "
			+ "order by regdate asc limit #{start}, #{replyPerPage}")
	public List<ReplyVO> list(@Param("bno") int bno, @Param("start") int start, 
			@Param("replyPerPage") int replyPerPage) throws Exception;
	
	@Select("select count(rno) from tbl_reply where bno=#{bno}")
	public int totalReplies(@Param("bno") int bno) throws Exception;

	@Insert("insert into tbl_reply (bno, replyer, replytext) "
			+ "values (#{bno}, #{replyer}, #{replytext})")
	public void add(ReplyVO replyVO) throws Exception;
	
	@Update("update tbl_board set replycnt = replycnt + 1 where bno=#{bno}")
	public void incReplycnt(@Param("bno") int bno) throws Exception;
	
	@Delete("delete from tbl_reply where rno=#{rno}")
	public void delete(@Param("rno") int rno) throws Exception;
	
	@Update("update tbl_board set replycnt = replycnt - 1 "
			+ "where bno=(select tbl_reply.bno from tbl_reply where rno=#{rno})")
	public void decReplycnt(@Param("rno") int rno) throws Exception;
	

	
	
	
	@Select("select * from tbl_board where bno=#{bno}")
	public BoardVO read(@Param("bno") int bno) throws Exception;
	
	@Update("update tbl_board set viewcnt = viewcnt + 1 where bno=#{bno}")
	public void incViewcnt(@Param("bno") int bno) throws Exception;
	

}
