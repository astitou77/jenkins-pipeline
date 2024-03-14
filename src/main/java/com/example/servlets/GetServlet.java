package com.example.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.beans.Employee;
import com.example.dao.ApplicationDao;



@SuppressWarnings("serial")
// @WebServlet("/search") // http://localhost:8080/HelloWorld/getAdnane?fieldVar=Aksadur
public class GetServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Example-2: Pull data from DAO (Data Access Object)
		String search = req.getParameter("SearchVar");
		ApplicationDao dao = new ApplicationDao();
		List<Employee> employeez = dao.searchEmployees(search);
		
		// writer.write("<html><h3>Employee Last Name is: "+employees.get(0).getLastName()+"<h3></html>"); 
		String result = getHTMLString(req.getServletContext().getRealPath("searchResults.html"), employeez );
		resp.getWriter().write(result);
	} 
	
	public String getHTMLString(String filePath, List<Employee> employees) throws IOException{
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
		if(employees.size() != 0) {
			page = MessageFormat.format(page, employees.get(0).getLastName());
		}else {
			page = MessageFormat.format(page, "Unknown :(");
		}
		return page;
	}

}