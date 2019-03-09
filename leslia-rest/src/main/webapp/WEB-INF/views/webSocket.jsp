<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
</body>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
    var sock = new SockJS('http://localhost:8081/webSocket');
    sock.onopen = function() {
        console.log('open');
        sock.send('客户端发送消息');
    };

    sock.onmessage = function(e) {
        console.log('message', e.data);
        sock.close();
    };

    sock.onclose = function() {
        console.log('close');
    };


</script>
</html>
