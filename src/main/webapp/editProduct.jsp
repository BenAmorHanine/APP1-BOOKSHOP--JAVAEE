<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.dao.ProductDAO, com.app.model.Product" %>
<%@ page import="java.sql.SQLException" %>

<%
    String productId = request.getParameter("id");
    Product product = null;

    if (productId != null) {
        ProductDAO productDAO = new ProductDAO();
        try {
            product = productDAO.getProductById(Integer.parseInt(productId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
    <h2>Edit Product</h2>

    <% if (product != null) { %>
        <form action="modifyProduct" method="post">
            <input type="hidden" name="id" value="<%= product.getId() %>">

            <label>Name:</label>
            <input type="text" name="name" value="<%= product.getName() %>" required><br>

            <label>Price:</label>
            <input type="number" name="price" step="0.01" value="<%= product.getPrice() %>" required><br>

            <label>Description:</label>
            <textarea name="description" required><%= product.getDescription() %></textarea><br>

            <label>Image URL:</label>
            <input type="text" name="imageUrl" value="<%= product.getImageUrl() %>"><br>

            <label>Category:</label>
            <input type="text" name="category" value="<%= product.getCategory() %>" required><br>

            <label>Title:</label>
            <input type="text" name="title" value="<%= product.getTitle() %>"><br>

            <label>Author:</label>
            <input type="text" name="author" value="<%= product.getAuthor() %>"><br>

            <button type="submit">Update Product</button>
        </form>
    <% } else { %>
        <p>Product not found.</p>
    <% } %>
</body>
</html>
