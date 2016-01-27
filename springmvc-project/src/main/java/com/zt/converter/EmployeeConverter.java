package com.zt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.zt.model.Department;
import com.zt.model.Employee;

/**
 * To change the format String to Employee object.
 * 
 * @Component that means give to spring IOC management.<br />
 * @author zengtao
 *
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {

	/**
	 * The converter method.To change the String to Employee object.
	 */
	public Employee convert(String source) {
		// TODO Auto-generated method stub
		if (source != null) {
			// split by char '-'
			String[] vals = source.split("-");

			// To validate the String array
			if (vals != null && vals.length == 4) {
				String lastName = vals[0];
				String email = vals[1];
				String gender = vals[2];
				Department department = new Department(
						Integer.parseInt(vals[3]));
				Employee employee = new Employee(null, lastName, email, gender,
						department);
				System.out.println(source + "--converter--" + employee);
				return employee;
			}
		}
		return null;
	}
}
