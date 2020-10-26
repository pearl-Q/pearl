package com.ssh.service.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.UserDAo;
import com.ssh.domain.User;
import com.ssh.service.UserService;
import com.ssh.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDAo userDao;
	
	public void setUserDao(UserDAo userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		//对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		//设置用户状态
		user.setUser_state("1");
		userDao.regist(user);
	}

	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

}
