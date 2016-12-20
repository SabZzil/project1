package org.sabzzil.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sabzzil.domain.UserVO;

public interface UserMapper {

	@Insert("insert into tbl_user (uid, upw, uname, email)"
			 + "values (#{uid}, #{upw}, #{uname}, #{email})")
	public void create(UserVO vo) throws Exception;
	
	@Select("select * from tbl_user where uid=#{uid}")
	public UserVO read(String uid) throws Exception;
	
	@Update("update tbl_user set upw=#{upw}, uname=#{uname},"
			+ " email=#{email} where uid=#{uid}")
	public void update(UserVO vo) throws Exception;
	
	@Delete("delete from tbl_user where uid=#{uid}")
	public void delete(String uid) throws Exception;
	
	@Select("select * from tbl_user where uid=#{uid} and upw=#{upw}")
	public UserVO login(UserVO userVO) throws Exception;
	
}
