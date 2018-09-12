<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
</body>
<script>
    var webSocket=new WebSocket("ws://localhost:8082/echo");

    webSocket.onopen= function (ev) {
        webSocket.send("客户端发送数据");
    }

    webSocket.onmessage= function (ev) {
        alert(ev.data);
    }

    webSocket.onclose=function (ev) {
    }

    webSocket.onerror=function (ev) {

    }

</script>
</html>
