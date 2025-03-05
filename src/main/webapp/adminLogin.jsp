<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>Admin Login</h2>
<form action="adminLogin" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>
<%
    if (request.getParameter("error") != null) {
        out.println("<p style='color:red;'>Invalid username or password!</p>");
    }
%>
</body>
</html>
