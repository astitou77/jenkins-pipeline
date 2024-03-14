package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  //com.mysql.jdbc.Driver 
			System.out.println("MySQL JDBC Driver Registered !");
			
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/helloDB", "debian-sys-maint", "IOTYffNlqJY5Rrf6");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/helloDB", "debian-sys-maint", "2FnSEOqO4U2Hvow6");
		}catch(ClassNotFoundException e){
			System.out.println("Where is your MySQL JDBC Driver ?");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("Connection to DB Failed ! Check output console");
			e.printStackTrace();
		}
		
		if(connection != null) {
			System.out.println("Connection made to DB !");
		}
		return connection;
	}
	
	// public static void main(String[] args) {
	// 	Connection myConn = DBConnection.getConnectionToDatabase();
	// }

}
