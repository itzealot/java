package com.zt.model;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * Model Employee
 * 
 * @author zengtao
 *
 */
public class Employee {

	// Employee's id
	private Integer id;

	// Employee's last name
	@NotEmpty
	private String lastName;

	// Employee's email
	@Length(min = 10, max = 20)
	@Email
	private String email;

	// Employee's sex
	private String gender;

	/**
	 * To use @DateTimeFormat to format the Date
	 */
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	/**
	 * To use the @NumberFormat to format the number
	 */
	@NumberFormat(pattern = "#,###,###.#")
	private Float salary;

	public Employee() {
		super();
	}

	public Employee(Integer id, String lastName, String email, String gender,
			Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	// One2N relation
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", gender=" + gender + ", birth=" + birth
				+ ", salary=" + salary + ", department=" + department + "]";
	}

}
