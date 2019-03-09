<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<form action="/sso/login" method="post">
username: <input type="text" id="username" name="username"><br><br>
password: <input type="password" id="password" name="password"><br><br>
<button id="loginBut" onclick="submit()">登录</button>
</form>

</body>

</html>
