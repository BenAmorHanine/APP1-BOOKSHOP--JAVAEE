<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <h2>Add New Product</h2>
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="add">
        Title: <input type="text" name="title" required><br>
        Author: <input type="text" name="author" required><br>
        Price: <input type="number" step="0.01" name="price" required><br>
        Quantity: <input type="number" name="quantity" required><br>
        <input type="submit" value="Add Product">
    </form>
</body>
</html>

