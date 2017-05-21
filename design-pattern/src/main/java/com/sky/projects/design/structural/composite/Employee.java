package com.sky.projects.design.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String name;
	private String dept;
	private int salary;

	// 员工列表
	private List<Employee> subordinates;

	public Employee(String name, String dept, int sal) {
		this.name = name;
		this.dept = dept;
		this.salary = sal;
		subordinates = new ArrayList<>();
	}

	/**
	 * 从员工列表中添加员工
	 * 
	 * @param e
	 */
	public void add(Employee e) {
		subordinates.add(e);
	}

	/**
	 * 从员工列表中移除员工
	 * 
	 * @param e
	 */
	public void remove(Employee e) {
		subordinates.remove(e);
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	@Override
	public String toString() {
		return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary + " ]");
	}
}