<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Product" %>
<%@ page import="com.app.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>


</head>
<body>
    <%
        // Redirect if the user is not logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <h2>Welcome, <%= user.getName() %>!</h2>
    <div class="container">
        <h1>Featured Products</h1>
        <div class="product-list">
            <!-- Loop through the products list -->
            <c:forEach var="product" items="${products}">
                <div class="product">
                    <img src="images/${product.imageUrl}" alt="${product.name}" class="product-image" />
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p>Price: $${product.price}</p>
                    <a href="product?id=${product.id}" class="btn">View Details</a>
                </div>
            </c:forEach>
        </div>
    </div>
    <a href="logout">Logout</a>

</body>
</html>
