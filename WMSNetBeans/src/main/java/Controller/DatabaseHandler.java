/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ewmsdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    /**
     * Establishes a connection to the database.
     * @return a Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
    
}
