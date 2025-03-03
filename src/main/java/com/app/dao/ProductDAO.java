package com.app.dao;

import com.app.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    // Database connection parameters
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";

    // Method to get all products
    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM products";  // Query to fetch all products
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                // Loop through the result set and create Product objects
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    product.setImageUrl(resultSet.getString("image"));
                    product.setCategory(resultSet.getString("category"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    // Method to get a product by ID
    public Product getProductById(int id) throws SQLException, ClassNotFoundException {
        Product product = null;

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    product.setImageUrl(resultSet.getString("imageUrl"));
                    product.setCategory(resultSet.getString("category"));
                }
            }
        }
        return product;
    }
}