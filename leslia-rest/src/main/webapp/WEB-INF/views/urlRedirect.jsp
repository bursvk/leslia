<%@ page import="com.leslia.rest.base.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://user.leslia.cn:8083/user/checkLogin" id="form">
    <input type="hidden" name="url" id="url" value="http://rest.leslia.cn:8081">
</form>
</body>
<script>
    function submit(){
        var form= document.getElementById("form")
        form.submit();
    }
    submit();
</script>
</html>
