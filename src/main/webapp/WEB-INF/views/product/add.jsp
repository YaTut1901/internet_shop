
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product addition</title>
</head>
<body>
<h1>Product addition</h1>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    name: <input type="text" name="productName"> <br/>
    price: <input type="number" name="productPrice"> <br/>
    <button type="submit">Add</button>
</form>
</body>
</html>