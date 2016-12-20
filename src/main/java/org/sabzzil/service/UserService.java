package org.sabzzil.service;

import org.sabzzil.domain.UserVO;

public interface UserService {
	
	public UserVO login(UserVO userVO) throws Exception;
}
