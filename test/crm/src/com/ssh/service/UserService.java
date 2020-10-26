package com.ssh.service;

import java.util.List;

import com.ssh.domain.User;

public interface UserService {
	public void save(User user);

	public User login(User user);

	public List<User> findAll();

}
