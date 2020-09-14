<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Please enter your credential!</h1>

<h4 style="color:red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    login <input type="text" name="login" required> <br />
    password <input type="password" name="pwd" required> <br />
    confirm password <input type="password" name="pwd_confirm" required> <br />
    name <input type="text" name="name" required> <br />

    <button type="submit">Register</button>
</form>
</body>
</html>
