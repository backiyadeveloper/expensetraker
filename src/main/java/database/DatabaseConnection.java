package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	 private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker_db";
	    private static final String USER = "backiya";
	    private static final String PASSWORD = "backiya@2001";
	   
	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
}