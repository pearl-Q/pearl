package com.qidi.ssh.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.qidi.ssh.dao.UserDao;
import com.qidi.ssh.pojo.User;
import com.qidi.ssh.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User login(User user) {
		return userDao.login(user);
	}
	@Override
	public void regist(User user) {
		userDao.regist(user);
	}

}
