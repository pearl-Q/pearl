<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员注册页面</title>
    <%--  静态包含 base css样式 JQuery  --%>
    <%@ include file="/pages/common/header.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word"></span>
    <%--  静态包含欢迎信息  --%>
    <%@ include file="/pages/common/success_menu.jsp"%>
</div>

<div id="main">

    <h1>注册成功! <a href="index.html">转到主页</a></h1>

</div>

<%--  静态包含页脚信息  --%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>