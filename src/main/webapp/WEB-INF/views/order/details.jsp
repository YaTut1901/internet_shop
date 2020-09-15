<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
</head>
<body>
<h1>Order details</h1>

<table border="2">
    <tr>
        <th>Name</th>
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
        </tr>
    </c:forEach>
</table>
<br />
<form action="${pageContext.request.contextPath}/order/user-orders">
    <input type="submit" value="Back" />
</form>
</body>
</html>
