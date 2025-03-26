<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <h2>Add New Product</h2>
    <form action="addProduct" method="post">
        <label>Name:</label>
        <input type="text" name="name" required><br>

        <label>Price:</label>
        <input type="number" name="price" step="0.01" required><br>


        <label>Image URL:</label>
        <input type="text" name="imageUrl" required><br>

        <label>Category:</label>
        <input type="text" name="category" required><br>

        <label>Author:</label>
        <input type="text" name="author"><br>

        <button type="submit">Add Product</button>
    </form>
</body>
</html>
