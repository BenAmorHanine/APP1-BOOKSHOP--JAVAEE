<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        Email: <input type="email" name="email" required><br>
        Password: <input type="password" name="password" required><br>
<%--        <input type="submit" value="Login">--%>
        <button type="submit">Login</button>
    </form>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red;'>Invalid email or password!</p>");
        }
    %>
        <p>Don't have an account? <a href="signup.jsp">Sign up here</a></p>

</body>
</html>