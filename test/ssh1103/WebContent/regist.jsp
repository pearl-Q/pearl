<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理系统</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user_regist.action" method="post">
			<h1>注册</h1><s:actionerror/>
			用户名：<input type="text" name="username" /><br><br>
			密码：<input type="password" name="password"/><br><br>
			说明：<input type="text" name="state" /><br><br>
			<input type="submit" value="注册"/>
			<a href="${pageContext.request.contextPath }/index.jsp">登录</a>
</form> 
</body>
</html>