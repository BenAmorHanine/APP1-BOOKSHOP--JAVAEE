<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Product" %>
<%@ page import="com.app.model.User" %>
<%@ page import="com.app.dao.ProductDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        .product-list { display: flex; flex-wrap: wrap; gap: 20px; }
        .product { border: 1px solid #ddd; padding: 10px; width: 200px; text-align: center; }
        .product img { width: 100px; height: 100px; object-fit: cover; }
        .btn { background: blue; color: white; padding: 5px 10px; text-decoration: none; }
    </style>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    ProductDAO productDAO = new ProductDAO();
    List<Product> products = null;
    try {
        products = productDAO.getAllProducts();
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    request.setAttribute("products", products);
%>

<h2>Welcome, <%= user.getName() %>!</h2>
<div class="container">
    <h1>Featured Products</h1>
    <div class="product-list">
        <c:forEach var="product" items="${products}">
            <div class="product">
                <h3>${product.name}</h3>
                <p>${product.author}</p>
                <p>Price: $${product.price}</p>
                <img src=${product.imageUrl} alt="${product.name}" />

            </div>
        </c:forEach>
    </div>
</div>
<center><a href="logout">Logout</a></center>
</body>
</html>
