<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main-menu</title>
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
<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Logout" />
</form>
<form action="${pageContext.request.contextPath}/inject">
    <input type="submit" value="Inject data" />
</form>
</body>
</html>
