<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User orders</title>
</head>
<body>
<h1>Your orders:</h1>

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
                <a href = "${pageContext.request.contextPath}/order/details?id=${order.id}" class=""> Details </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.request.contextPath}/main-menu">
    <input type="submit" value="Main menu" />
</form>
</body>
</html>
