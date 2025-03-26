package com.app.controller;

import com.app.dao.ProductDAO;
import com.app.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        String category = request.getParameter("category");

        Product product = new Product();
        product.setName(name);
        product.setAuthor(author);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setCategory(category);

        try {
            productDAO.addProduct(product);
            response.sendRedirect("adminDashboard.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error adding product", e);
        }
    }
}
