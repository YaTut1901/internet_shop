<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>All orders</h1>

<table border="2">
    <tr>
        <th>Order</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/admin/delete-order?id=${order.id}" class=""> Delete </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
