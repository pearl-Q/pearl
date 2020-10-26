package com.ssh.web.interceptor;

import org.apache.struts2.ServletActionContext;
import org.eclipse.jdt.internal.compiler.ast.Invocation;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ssh.domain.User;

public class privilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		//检查 Session 是否有对象
		User existUser =(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//判断从 session 中获取的用户是否为空
		if(existUser==null) {
			//无登录
			ActionSupport actionSupport =(ActionSupport) invocation.getAction();
			actionSupport.addActionError("没有登录，没有权限");
			return actionSupport.LOGIN;
		}else {
			return invocation.invoke();
		}
	}

}
