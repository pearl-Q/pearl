package com.ssh.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.User;
import com.ssh.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}
	public String login() {
		User existUser = userService.login(user);
		if(existUser==null) {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}else {
			return SUCCESS;
		}
	}
}
