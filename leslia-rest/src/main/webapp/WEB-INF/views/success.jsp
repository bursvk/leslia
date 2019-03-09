<%@ page import="com.leslia.api.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

登录成功 <br>
用户名：<%=session.getAttribute("username")%> <br>

<a href="/logout">退出登录</a>

</body>


</html>
