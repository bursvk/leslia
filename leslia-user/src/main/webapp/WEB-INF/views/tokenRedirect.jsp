<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${url}" id="form">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="userId" value="${userId}">
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
