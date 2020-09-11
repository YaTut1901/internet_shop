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
        <th>Position</th>
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
        </tr>
    </c:forEach>
</table>
<form method="get" action="${pageContext.request.contextPath}/product/add">
    <button type="submit">Add product</button>
</form>
<form method="post" action="${pageContext.request.contextPath}/product/buy">
    Enter product id to buy: <br />
    id <input type="number" name="productId"> <br />
    <button type="submit">Buy product</button>
</form>
</body>
</html>
