<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Product</title>
</head>
<body>
    <h2>Modify Product</h2>
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="modify">
        Product ID: <input type="number" name="id" required><br>
        New Title: <input type="text" name="title"><br>
        New Author: <input type="text" name="author"><br>
        New Price: <input type="number" step="0.01" name="price"><br>
        New Quantity: <input type="number" name="quantity"><br>
        <input type="submit" value="Modify Product">
    </form>
</body>
</html>
