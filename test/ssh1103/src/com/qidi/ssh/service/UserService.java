package com.qidi.ssh.service;

import com.qidi.ssh.pojo.User;

public interface UserService {
	User login(User user);

	void regist(User user);
}
