package com.zt.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zt.model.Department;

/**
 * ��̬�����ݣ���δ�������ݿ�<br />
 * ʹ��@Repository����Spring�������� <br />
 * 
 * @author zengtao
 *
 */

@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;

	// To init the departments map
	static {

		departments = new HashMap<Integer, Department>();

		departments.put(101, new Department(101, "D-AA"));
		departments.put(102, new Department(102, "D-BB"));
		departments.put(103, new Department(103, "D-CC"));
		departments.put(104, new Department(104, "D-DD"));
		departments.put(105, new Department(105, "D-EE"));
	}

	/**
	 * To get all departments from the map.
	 * 
	 * @return all the Department objects that was saved in Collection.
	 */
	public Collection<Department> getDepartments() {
		// ����Map ������valueֵ
		return departments.values();
	}

	/**
	 * To get the department by department's id.
	 * 
	 * @param id
	 * @return the Department object or null.
	 */
	public Department getDepartmentById(Integer id) {
		if (id == null || !departments.containsKey(id)) {
			return null;
		}
		return departments.get(id);
	}
}
