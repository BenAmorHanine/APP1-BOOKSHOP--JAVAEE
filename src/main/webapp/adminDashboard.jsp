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
</head>
<body>
<h2>Welcome, Admin</h2>
<a href="adminLogout">Logout</a>

<h3>Manage Books</h3>

<!-- Buttons to manage products -->
<div>
    <a href="addProduct.jsp"><button>Add New Book</button></a>
<%--    <a href="editProduct.jsp"><button>Edit Book</button></a>--%>
<%--    <a href="deleteProduct.jsp"><button>Delete Book</button></a>--%>
</div>

<!-- Table to display products -->
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <% for (Product product : products) { %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getAuthor() %></td>
        <td><%= product.getPrice() %></td>
        <td>
            <a href="editProduct.jsp?id=<%= product.getId() %>">Edit</a> |
            <a href="deleteProduct?id=<%= product.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
