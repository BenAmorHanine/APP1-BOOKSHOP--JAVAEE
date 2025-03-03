<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
    <h2>Sign Up</h2>

    <%-- Display error message if sign-up fails --%>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Error: Email already exists or something went wrong!</p>
    <% } %>

    <form action="register" method="post">
        <label for="fullname">Full Name:</label>
        <input type="text" name="fullname" id="fullname" required>
        <br>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        <br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
        <br>

        <button type="submit">Sign Up</button>
    </form>

    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>
