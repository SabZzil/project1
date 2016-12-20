package org.sabzzil.persistence;

import org.sabzzil.domain.UserVO;
import org.sabzzil.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserVO login(UserVO userVO) throws Exception {
		return mapper.login(userVO);
	}
}
