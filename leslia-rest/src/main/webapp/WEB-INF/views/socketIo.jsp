<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/14 0014
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消息推送</title>
</head>
<body>

</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/socket.io/2.1.1/socket.io.js"></script>
<script type="text/javascript">
    <!-- 实时从指定查看是否有消息 -->
    var socket = io.connect('localhost:9092');
    socket.on('connect_msg',function(data){
        alert(data);
        console.log(data);
    });

</script>
</html>
