package com.ssh.dao;

import com.ssh.domain.User;

public interface UserDAo {
	public void regist(User user);

	public User login(User user);
}
