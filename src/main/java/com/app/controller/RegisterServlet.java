package com.app.controller;

import com.app.dao.UserDAO;
import com.app.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register") // Maps to the form's action
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create user object
        User newUser = new User(name, email, password);

        try {
            // Try registering the user
            boolean isRegistered = userDAO.registerUser(newUser) ;

            if (isRegistered) {
                response.sendRedirect("login.jsp"); // Redirect to login page after success
            } else {
                response.sendRedirect("signup.jsp?error=1"); // Show error message if registration fails
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=1");
        }
    }
}
