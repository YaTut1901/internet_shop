<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Entrance</title>
</head>
<body>
<h1>Enter your credential to sign in</h1>

<h4 style="color:red">${errMsg}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    login <input type="text" name="login" required> <br />
    password <input type="password" name="pwd" required> <br />

    <button type="submit">Enter</button>
</form>
<form action="${pageContext.request.contextPath}/registration">
    <input type="submit" value="Register" />
</form>
</body>
</html>
