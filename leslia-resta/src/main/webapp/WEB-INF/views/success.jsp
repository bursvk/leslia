<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
登录成功
${message}
<% String userId=(String)session.getAttribute("userId"); %>
用户ID：<%=userId%>


<a href="/user/logout">退出登录</a>


</body>



</html>
