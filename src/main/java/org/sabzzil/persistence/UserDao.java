package org.sabzzil.persistence;

import org.sabzzil.domain.UserVO;

public interface UserDao {

	public UserVO login(UserVO userVO) throws Exception;
}
