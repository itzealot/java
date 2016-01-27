package com.zt.handler;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zt.dao.DepartmentDao;
import com.zt.dao.EmployeeDao;
import com.zt.model.Employee;

/**
 * The controller about Employee named EmployeeHandler
 * 
 * @author zengtao
 *
 */
@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getEmployees());
		return "list";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	/**
	 * To add the employee
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		// To get all the depts
		map.put("depts", departmentDao.getDepartments());

		// new a empty Employee object to use for form table view
		map.put("employee", new Employee());
		return "input";
	}

	/**
	 * To save the Employee
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result,
			Map<String, Object> map) {

		if (result.getErrorCount() > 0) {// has errors
			System.out.println("validate error!");

			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + " : "
						+ error.getDefaultMessage());
			}

			// has errors then return the input page
			map.put("depts", departmentDao.getDepartments());
			return "input";
		}
		System.out.println("save :" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	/**
	 * To delete the employee by id.The RequestMethod must be DELETE
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	/**
	 * ���������ʹ��ͬһ����������Ϊһ����GET��һ����POST������һ����Ҫһ��@PathVariable���ε�id�����ڻ��Ե�ǰid�µ�����
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		// To set the value into map
		map.put("employee", employeeDao.getEmployeeById(id));
		map.put("depts", departmentDao.getDepartments());
		return "input";
	}

	/**
	 * update��������û��ʹ��@ModelAttributeע��������Σ����������Ϊ������һ����ĸСд
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	/**
	 * ʹ��@ModelAttribute���εķ��������ڽ��������ʱ�������˲����޸�ֵ�����⣻<br />
	 * ��������Integer id��Map<String, Object> map.<br />
	 * 
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getEmployee(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map) {
		if (id != null) {// is update
			map.put("employee", employeeDao.getEmployeeById(id));
		}
	}

	/**
	 * The converters about mine's handler method.<br />
	 * To save the employee by format String.
	 */
	@RequestMapping("/testConverter")
	public String testConverter(@RequestParam("employee") Employee employee) {
		System.out.println("save: " + employee);
		employeeDao.save(employee);
		// To redirect the employees list page
		return "redirect:/emps";
	}

	/**
	 * To get all Employees and return Collection<Employee>.<br />
	 * To use the @ResponseBody to transport the jackson pattern data.<br />
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson() {
		return employeeDao.getEmployees();
	}
}
