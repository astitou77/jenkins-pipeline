package com.example.beans;

public class Employee {
	// variables
	private String firstName;
	private String lastName;
	//private int experience;
	
	// constructor
	public Employee() {

	}
	public Employee(String name, String last) {
		this.firstName = name;
		this.lastName = last;
	}
	
	// getters
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	//setters
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public void setLastName(String last) {
		this.lastName = last;
	}

	// Testing 'Employee' bean
    
	// public static void main(String[] args) {
	// 	Employee roda = new Employee("Roda", "Omar");
	// 	System.out.println(roda.getFirstName());
	// 	System.out.println(roda.getLastName());
	// }
}
