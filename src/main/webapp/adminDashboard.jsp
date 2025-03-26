<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Product" %>
<%@ page import="com.app.dao.ProductDAO" %>
<%@ page import="java.sql.SQLException" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }

    ProductDAO productDAO = new ProductDAO();
    List<Product> products = null;
    try {
        products = productDAO.getAllProducts();
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
        img { width: 50px; height: 50px; object-fit: cover; }
        .container { max-width: 1200px; margin: auto; }
        button { padding: 10px; background-color: green; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, Admin</h2>
    <a href="adminLogout">Logout</a>
    <h3>Manage Books</h3>
    <a href="addProduct.jsp"><button>Add New Book</button></a>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Category</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        <% for (Product product : products) { %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getAuthor() != null ? product.getAuthor() : "Unknown" %></td>
            <td>$<%= product.getPrice() %></td>
            <td><%= product.getCategory() %></td>
            <td><img src="<%= product.getImageUrl() %>" width="50"></td>

            <td>
                <a href="editProduct.jsp?id=<%= product.getId() %>">Edit</a> |
                <a href="deleteProduct?id=<%= product.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
