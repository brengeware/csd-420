/*
 Brennan Cheatwood
CSD420 - Assignment 10.2
5/24/26
 */
package fansapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FanDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/databasedb?useSSL=false";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    //returns a fan object by ID, or null is not found
    public Fan getFanByID(int id) {
        String sql = "SELECT * FROM fans WHERE ID = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Fan(
                rs.getInt("ID"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("favoriteteam"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR - Retrieving fan: " + e.getMessage());
        }
        return null;
    }
    
    //updates an existing fan record, returns true if successful
    public boolean updateFan(Fan fan) {
        String sql = "UPDATE fans SET firstname=?, lastname=?, favoriteteam=? WHERE ID=?";
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, fan.getFirstname());
            stmt.setString(2, fan.getLastname());
            stmt.setString(3, fan.getFavoriteteam());
            stmt.setInt(4, fan.getID());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.out.println("ERROR - Updating fan: " + e.getMessage());
        }
        return false;
    }
    
    
    
}
