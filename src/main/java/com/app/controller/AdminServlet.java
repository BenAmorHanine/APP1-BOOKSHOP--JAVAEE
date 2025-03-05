package com.app.controller;

import com.app.dao.AdminDAO;
import com.app.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminDAO.getAdminByCredentials(username, password);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            System.out.println("Admin logged in");
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("adminLogin.jsp?error=1");
        }
    }
}

