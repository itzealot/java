package com.zt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zt.model.Department;
import com.zt.model.Employee;

@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;

	@Autowired
	private DepartmentDao dao;

	/**
	 * To init data to pretend to operate database
	 */
	static {
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "E-AA", "aa@qq.com", "boy",
				new Department(101)));

		employees.put(1002, new Employee(1002, "E-BB", "bb@qq.com", "girl",
				new Department(102)));

		employees.put(1003, new Employee(1003, "E-CC", "cc@qq.com", "boy",
				new Department(101)));

		employees.put(1004, new Employee(1004, "E-DD", "dd@qq.com", "girl",
				new Department(103)));

		employees.put(1005, new Employee(1005, "E-EE", "ee@qq.com", "boy",
				new Department(104)));
	}

	public static Integer initId = 1006;

	/**
	 * To get all employees.
	 * 
	 * @return Collection<Employee> object
	 */
	public Collection<Employee> getEmployees() {
		return employees.values();
	}

	/**
	 * To get the employee by id.
	 * 
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(Integer id) {
		if (id == null || !employees.containsKey(id)) {
			return null;
		}
		return employees.get(id);
	}

	/**
	 * To for each the Map to get the employees where is the same department,
	 * which the departmentId := deptId.
	 * 
	 * @param deptId
	 * @return
	 */
	public Collection<Employee> getEmployeesByDeptId(Integer deptId) {
		Collection<Employee> emps = new ArrayList<Employee>();

		for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
			// Integer key = entry.getKey();
			Employee employee = entry.getValue();
			Department dept = employee.getDepartment();

			// If the deptId is equals then is the same department
			if (dept != null && dept.getId() == deptId) {
				emps.add(employee);
			}
		}
		return emps;
	}

	/**
	 * To delete the employee by id from the employees map
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		return employees.remove(id) == null ? false : true;
	}

	/**
	 * To add the employee
	 * 
	 * @param employee
	 * @return
	 */
	public boolean save(Employee employee) {
		// is save that means don't have the id
		if (employee.getId() == null) {
			employee.setId(initId++);
		}

		// To set the department's info
		employee.setDepartment(dao.getDepartmentById(employee.getDepartment()
				.getId()));
		employees.put(employee.getId(), employee);
		return true;
	}

}
