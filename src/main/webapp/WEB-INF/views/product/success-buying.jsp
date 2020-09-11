<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 10.09.2020
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>BUYING SUCCESS!</h1>
<form method="get" action="${pageContext.request.contextPath}/shopping-cart/cart">
    <button type="submit">Open cart</button>
</form>
</body>
</html>
