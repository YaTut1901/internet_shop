<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>All products in the store</h1>

    <table border="1">
    <tr>
        <th>ID</th>
        <th>Product</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/product/buy?id=${product.id}"> Buy </a>
            </td>
        </tr>
    </c:forEach>
</table>
<form method="get" action="${pageContext.request.contextPath}/product/add">
    <button type="submit">Add product</button>
</form>
</body>
</html>
