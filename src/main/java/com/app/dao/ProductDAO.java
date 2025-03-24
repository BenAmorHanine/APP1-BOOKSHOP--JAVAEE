package com.app.dao;

import com.app.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    // Database connection parameters
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/bookshop";
    private static final String dbUser = "root";
    private static final String dbPassword = "";

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
        }catch (SQLException e) {
            // Afficher l'erreur complète pour diagnostiquer
            System.out.println("Erreur SQL lors de la récupération des produits : " + e.getMessage());
            e.printStackTrace();  // Afficher la pile d'appel pour plus de détails
            throw e;
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

    public void addProduct(Product product) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO products (name, price, description, image, category) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setString(3, product.getDescription());
        statement.setString(4, product.getImageUrl());
        statement.setString(5, product.getCategory());

        statement.executeUpdate();
    }
}

public void updateProduct(Product product) throws SQLException, ClassNotFoundException {
    String sql = "UPDATE products SET name=?, price=?, description=?, image=?, category=? WHERE id=?";

    try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setString(3, product.getDescription());
        statement.setString(4, product.getImageUrl());
        statement.setString(5, product.getCategory());
        statement.setInt(6, product.getId());

        statement.executeUpdate();
    }
}

}
