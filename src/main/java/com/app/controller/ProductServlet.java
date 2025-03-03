package com.app.controller;

import com.app.dao.ProductDAO;
import com.app.model.Product;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the session exists and contains a valid user (authentication check)
        HttpSession session = request.getSession(false); // Get the session if it exists, otherwise return null
        if (session == null || session.getAttribute("user") == null) {
            // No session or user is not authenticated, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Create ProductDAO instance and fetch all products
        ProductDAO productDAO = new ProductDAO();
        try {
            List<Product> products = productDAO.getAllProducts(); // Fetch products from the database
            request.setAttribute("products", products); // Set products as request attribute to be accessed in the JSP
            request.getRequestDispatcher("home.jsp").forward(request, response); // Forward to home.jsp
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("error", "An error occurred while fetching products.");
            request.getRequestDispatcher("home.jsp").forward(request, response); // Forward to home.jsp with error message
        }
    }
}