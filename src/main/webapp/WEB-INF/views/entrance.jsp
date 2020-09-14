<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Entrance</title>
</head>
<body>
<h1>Enter your credential to sign in</h1>

<form method="post" action="${pageContext.request.contextPath}/entrance">
    login <input type="text" name="login" required> <br />
    password <input type="password" name="pwd" required> <br />

    <button type="submit">Enter</button>
</form>
</body>
</html>
