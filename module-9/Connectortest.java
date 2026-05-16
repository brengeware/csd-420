/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectortest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brennan
 */
public class Connectortest {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/databasedb",
                    "student1",
                    "pass");
            System.out.println("Connection Successful! " + conn);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }
    
}
