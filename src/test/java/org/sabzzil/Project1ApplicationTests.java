package org.sabzzil;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sabzzil.domain.BoardVO;
import org.sabzzil.domain.UserVO;
import org.sabzzil.mapper.BoardMapper;
import org.sabzzil.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Project1ApplicationTests {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSession;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testConnection() throws Exception {
		System.out.println(ds);
		Connection con = ds.getConnection();
		System.out.println(con);
		con.close();
	}
	
	@Test
	public void testSqlSession() throws Exception {
		System.out.println(sqlSession);
	}
	
	@Test
	public void testInsert() throws Exception {
		UserVO vo = new UserVO();
		
		vo.setUid("user543");
		vo.setUpw("user543");
		vo.setUname("Park");
		vo.setEmail("test@test.com");
		
		userMapper.create(vo);
	}
	
	@Test
	public void testXmlMapper() throws Exception {
		int bno = 470;
		BoardVO boardVO;
		boardVO = boardMapper.test(bno);
		System.out.println(boardVO.getContent());
	}

}
