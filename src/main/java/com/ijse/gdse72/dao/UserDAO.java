package com.ijse.gdse72.dao;


import com.ijse.gdse72.model.User;
import com.ijse.gdse72.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO {

    public User authenticate(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";

        try(Connection connection = DBConnection.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User user = new User();

                user.setU_id(rs.getString("u_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

               if(rs.getString("password").equals(password)){
                   return user;
               }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user) {
        String sql = "insert into users(u_id, username, password, role) values(?,?,?,?)";

        try(Connection connection = DBConnection.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getU_id());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateId(){

        String sql = "SELECT u_id FROM users ORDER BY u_id DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection()){

             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String lastId = rs.getString("u_id");
                int num = Integer.parseInt(lastId.substring(1));
                num++;
                return String.format("%s%03d", "U", num);
            } else {

                return "U001";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error generating user ID", e);
        }
    }
}
