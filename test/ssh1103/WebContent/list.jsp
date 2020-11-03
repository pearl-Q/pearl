<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户信息列表</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/customer_list.action" method="post">
商品列表：
<table width="100%" border=1>
<tr>
	<td>客户名称</td>
	<td>电话</td>
	<td>手机</td>
	<td>操作</td>

	
</tr>
<c:forEach items="${list}" var="customer">
<TR
	style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
	<TD>${customer.cust_name }</TD>
	<TD>${customer.cust_phone }</TD>
	<TD>${customer.cust_mobile }</TD>
	<TD>
	<a href="${pageContext.request.contextPath }/customer_edit.action?method=post&cust_id=${customer.cust_id}">修改</a>
	&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath }/customer_delete.action?method=post&cust_id=${customer.cust_id}">删除</a>
		</TD>
	</TR>
</c:forEach>
</table>
</form>
</body>
<TR>
		<TD class=menuSmall><A class=style2 target="_self" href="${ pageContext.request.contextPath}/add.jsp" 
			target=main> 新增客户</A></TD>
	</TR>
</body>
</html>