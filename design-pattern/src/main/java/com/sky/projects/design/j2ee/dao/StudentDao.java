package com.sky.projects.design.j2ee.dao;

import java.util.List;

public interface StudentDao {
	List<Student> getAllStudents();

	Student getStudent(int rollNo);

	void updateStudent(Student student);

	void deleteStudent(Student student);
}
