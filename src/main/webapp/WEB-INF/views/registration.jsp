<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Please enter your credential!</h1>

<h4 style="color:red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    login <input type="text" name="login"> <br />
    password <input type="password" name="pwd"> <br />
    confirm password <input type="password" name="pwd_confirm"> <br />
    name <input type="text" name="name"> <br />

    <button type="submit">Register</button>
</form>
</body>
</html>
