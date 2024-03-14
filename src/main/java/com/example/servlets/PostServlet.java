package com.example.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
//import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.beans.Employee;
import com.example.dao.ApplicationDao;

@SuppressWarnings("serial")
//@WebServlet("add")
public class PostServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Servlet 'doPost' method of 'AddEmployeeServlet' was called ");
		ApplicationDao myAppDao = new ApplicationDao();
		
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		
		Employee newEmployee = new Employee(firstName, lastName);
		myAppDao.saveEmployees(newEmployee);
		System.out.println("EMPLOYEE REGISTERED ! " + firstName + ", " + lastName);
		
		// writer.write("<html><h3>Employee Last Name is: "+employees.get(0).getLastName()+"<h3></html>"); 
		String result = getHTMLString(req.getServletContext().getRealPath("addEmployeeResults.html"), newEmployee );
		resp.getWriter().write(result);
	}

	public String getHTMLString(String filePath, Employee newEmployee) throws IOException{
		// Save entire HTML into 1 String variable
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine())!=null) {
			buffer.append(line);
		}
		
		reader.close();
		String page  = buffer.toString();
		
		// Replace Last name placeholder with Last Name (corresponding the First Name provided) 
		// retrieved from DB
		if(newEmployee != null) {
			page = MessageFormat.format(page, newEmployee.getFirstName() + " " + newEmployee.getLastName());
		}else {
			page = MessageFormat.format(page, "Unknown :(");
		}
		return page;
	}
	

}