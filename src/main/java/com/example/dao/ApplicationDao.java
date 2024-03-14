package com.example.dao;
import com.example.beans.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ApplicationDao {

	public List<Employee> searchEmployees(String searchString){
		Employee employee = null;
		List<Employee> employees = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from employees where first_name like '%"+searchString+"%'";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			
			while(set.next()){
				employee = new Employee();
				employee.setFirstName(set.getString("first_name"));
				employee.setLastName(set.getString("last_name"));
				//employee.setExperience(set.getInt("experience"));
				
				employees.add(employee);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	public int saveEmployees(Employee emp) {
		int rowsAffected = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "INSERT INTO employees VALUES (?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, emp.getFirstName());
			statement.setString(2, emp.getLastName());
			
			rowsAffected = statement.executeUpdate();
			
		}catch(SQLException e) {e.printStackTrace();}
		
		System.out.println("rows updated: " + rowsAffected);
		return rowsAffected;
	}

	// public static void main(String[] args) {
	// 	ApplicationDao myAppDao = new ApplicationDao();
		
	// 	List<Employee> employeez = myAppDao.searchEmployees("Adnane");
	// 	System.out.println(employeez.get(0).getLastName() + "\nweeee\n");
		
	// 	Employee wayne = new Employee("Free", "Palestine");
	// 	myAppDao.saveEmployees(wayne);
	// }
}
