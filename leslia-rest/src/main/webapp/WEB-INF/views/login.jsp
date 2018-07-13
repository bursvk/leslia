<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/11 0011
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<form action="/user/login" method="post">
username: <input type="text" id="username" name="username"><br><br>
password: <input type="password" id="password" name="password"><br><br>
<button id="loginbtn" onclick="submit()">登录</button>
</form>

</body>

</html>
