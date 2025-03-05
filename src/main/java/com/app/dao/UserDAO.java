package com.app.dao;

import com.app.model.User;

import java.sql.*;

public class UserDAO {
    // Method to create a new user
    public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
        // Set up JDBC connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/bookshop";
        String dbUser = "root";
        String dbPassword = "";

        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Initialize connection and statement
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            // Define the SQL query to insert a new user
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set the values for the user
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());

                // Execute the update (insert)
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle SQL exception (could log it)
            throw new SQLException("Error creating user: " + e.getMessage(), e);
        }
        return true;
    }

    // Method to check login credentials
    public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        // Set up JDBC connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/bookshop"; // Your database name
        String dbUser = "root"; // Your database username
        String dbPassword = ""; // Your database password

        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Declare the user object
        User user = null;

        // Initialize connection, statement, and result set
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            // Define the SQL query to check the login credentials
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                // Execute the query
                try (ResultSet result = statement.executeQuery()) {
                    // If a user is found, create the User object and set the necessary properties
                    if (result.next()) {
                        user = new User();
                        user.setId(result.getInt("id")); // Assuming you have an 'id' field in the users table
                        user.setName(result.getString("name"));
                        user.setEmail(result.getString("email"));
                        user.setPassword(result.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception (could log it)
            throw new SQLException("Error checking login credentials: " + e.getMessage(), e);
            /*e.printStackTrace();
            throw e;*/
        }

        // Return the user (null if no match was found)
        return user;
    }

}