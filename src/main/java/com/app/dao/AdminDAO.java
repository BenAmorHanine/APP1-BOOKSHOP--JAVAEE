package com.app.dao;

import com.app.model.Admin;
import com.app.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/bookshop";
    private static final String dbUser = "root";
    private static final String dbPassword = "";


    public Admin getAdminByCredentials(String username, String password) {
        Admin admin = null;
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
