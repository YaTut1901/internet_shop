<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/delete">
    Enter username to delete: <input type="text" name="login"> <br/>
    <button type="submit">Delete</button>
</form>
</body>
</html>
