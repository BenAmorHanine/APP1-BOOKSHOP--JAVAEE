package com.app.controller;

import com.app.dao.UserDAO;
import com.app.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Handle GET request to display login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // Handle POST request for login authentication
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve email and password from the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create UserDAO instance to check credentials
        UserDAO userDAO = new UserDAO();
        try {
            // Check if the user exists with the provided email and password
            User user = userDAO.checkLogin(email, password);

            if (user != null) {
                // If the user is found, create a session for the user
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Store the user object in the session

                // Redirect to the home page
                response.sendRedirect("home.jsp");
            } else {
                // If no match is found, redirect back to login page with error
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle database errors
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
