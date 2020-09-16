<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>MAIN MENU</h1>
<form action="${pageContext.request.contextPath}/admin/products">
    <input type="submit" value="Change assortment" />
</form>
<form action="${pageContext.request.contextPath}/admin/orders">
    <input type="submit" value="Orders" />
</form>
<form action="${pageContext.request.contextPath}/user/all">
    <input type="submit" value="Users" />
</form>
<form action="${pageContext.request.contextPath}/product/all">
    <input type="submit" value="Products" />
</form>
<form action="${pageContext.request.contextPath}/shopping-cart/products">
    <input type="submit" value="My cart" />
</form>
<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Logout" />
</form>
</body>
</html>
