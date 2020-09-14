<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>BUYING SUCCESS!</h1>
<form method="get" action="${pageContext.request.contextPath}/shopping-cart/products">
    <button type="submit">Open cart</button>
</form>
<form method="get" action="${pageContext.request.contextPath}/product/all">
    <button type="submit">Buy another item</button>
</form>
</body>
</html>
