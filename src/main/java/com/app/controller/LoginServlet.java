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
import com.app.utils.SessionUtil;
import com.app.utils.Constants;
import com.app.utils.Modules;

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
            User user = userDAO.checkLogin(email, password);

            if (user != null) {
                // Créer la session utilisateur
                SessionUtil.createUserSession(request, user);

                // ✅ Création de cookies pour cet utilisateur
                Modules.setUserCookies(response, user);

                // ✅ Redirection vers la page d'accueil
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");
        }
    }
}