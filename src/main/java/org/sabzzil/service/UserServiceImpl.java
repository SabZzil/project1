package org.sabzzil.service;

import org.sabzzil.domain.UserVO;
import org.sabzzil.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public UserVO login(UserVO userVO) throws Exception {
		return dao.login(userVO);
	}
	
}
