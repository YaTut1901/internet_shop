<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Your cart</h1>

<h4 style="color:green">${message}</h4>

<table border="2">
    <tr>
        <th>Position</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/shopping-cart/delete-item?id=${product.id}" class=""> Delete </a>
            </td>
        </tr>
    </c:forEach>
</table> <br />
<form action="${pageContext.request.contextPath}/main-menu">
    <input type="submit" value="Main menu" />
</form>
<form action="${pageContext.request.contextPath}/order/create">
    <input type="submit" value="Create order" />
</form>
<form action="${pageContext.request.contextPath}/order/user-orders">
    <input type="submit" value="My orders" />
</form>
</body>
</html>
