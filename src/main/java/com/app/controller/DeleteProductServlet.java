package com.app.controller;

import com.app.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Vérifier si l'admin est connecté
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();

        try {
            productDAO.deleteProduct(productId);
            response.sendRedirect("adminDashboard.jsp?message=deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=delete_failed");
        }
    }
}

