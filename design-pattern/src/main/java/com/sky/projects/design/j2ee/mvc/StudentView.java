package com.sky.projects.design.j2ee.mvc;

/**
 * 创建视图
 * 
 * @author zealot
 *
 */
public class StudentView {
	/**
	 * show Model's info
	 * 
	 * @param studentName
	 * @param studentRollNo
	 */
	public void printStudentDetails(String studentName, String studentRollNo) {
		System.out.println("Student: ");
		System.out.println("Name: " + studentName);
		System.out.println("Roll No: " + studentRollNo);
	}
}
