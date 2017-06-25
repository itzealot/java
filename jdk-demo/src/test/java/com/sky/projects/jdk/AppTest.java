package com.sky.projects.jdk;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	/**
	 * 字符串采用 UTF-8编码
	 */
	public void testStringLen() {
		System.out.println("a".length());
		System.out.println("我爱你".length());
		System.out.println("我love".length());
	}

	public void testTreeMap() {
		Map<Student, String> map = new TreeMap<>();

		// key必须实现Comparable<T>接口或者指定比较器
		map.put(new Student("1"), "1");
		map.put(new Student("2"), "2");

		System.out.println(map);
	}

	static class Student implements Comparable<Student> {
		private String id;

		public Student(String id) {
			super();
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public int compareTo(Student o) {
			return o == null ? -1 : id.compareTo(o.getId());
		}

		@Override
		public String toString() {
			return "Student [id=" + id + "]";
		}
	}

}
