<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello customer!</h1>
<form action="${pageContext.request.contextPath}/registration">
    <input type="submit" value="Registration" />
</form>
Already have an account? <br /> <br />
<form action="${pageContext.request.contextPath}/login">
    <input type="submit" value="Enter" />
</form>
</body>
</html>
