package com.sky.projects.jdk.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import junit.framework.TestCase;

public class ComparatorTest extends TestCase {

	Student[] students = new Student[12];

	{
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			students[i] = new Student("prefix-" + random.nextInt(5), random.nextInt(5));
		}

		students[10] = new Student(null, 0);
		students[11] = new Student(null, 1);
	}

	/**
	 * 返回0表示相等 <br>
	 * 返回正数表示大于 <br>
	 * 返回负数表示小于 <br>
	 * 先排出对应的asc，而后desc则取负
	 */
	public void testNameAscAgeAsc() {
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getName() == null && o2.getName() == null) {
					return 0;
				} else if (o2.getName() == null) {
					// o2 放后(o1.name<o2.name=null)
					return -1;
				} else if (o1.getName() == null) {
					// 当 o1.name=null>o2.name，返回正数
					return 1;
				} else {
					int compareTo = o1.getName().compareTo(o2.getName());
					if (compareTo != 0) {
						// 当 o1.name>o2.name时，返回正数，更大的(o1.name)放后面
						// 当 o1.name<o2.name时，返回负数，更小(o1.name)的放前面
						return compareTo;
					}

					// 返回正数，则 o1.age>o2.age，更大的(o1.age)放后面
					// 返回负数，则 o1.age<o2.age，更小的(o1.age)放前面
					return o1.getAge() - o2.getAge();
				}
			}
		});

		System.out.println("after sort--------------");
		print(students);
		System.out.println("after sort--------------");
	}

	public void testNameDescAgeAsc() {
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getName() == null && o2.getName() == null) {
					return 0;
				} else if (o2.getName() == null) {
					// o2 放后(o1.name<o2.name=null)
					return -1;
				} else if (o1.getName() == null) {
					// o1 放后(o1.name=null>o2.name)
					return 1;
				} else {
					int compareTo = o1.getName().compareTo(o2.getName());
					if (compareTo != 0) {
						return -compareTo;
					}

					return o1.getAge() - o2.getAge();
				}
			}
		});

		System.out.println("after sort--------------");
		print(students);
		System.out.println("after sort--------------");
	}

	public void testNameDescAgeDesc() {
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getName() == null && o2.getName() == null) {
					return 0;
				} else if (o2.getName() == null) {
					// o2 放后(o1.name<o2.name=null)
					return -1;
				} else if (o1.getName() == null) {
					// o1 放后(o1.name=null>o2.name)
					return 1;
				} else {
					int compareTo = o1.getName().compareTo(o2.getName());
					if (compareTo != 0) {
						return -compareTo;
					}

					return o2.getAge() - o1.getAge();
				}
			}
		});

		System.out.println("after sort--------------");
		print(students);
		System.out.println("after sort--------------");
	}

	static final class Student {
		private final String name;
		private final int age;

		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}
	}

	static final void print(Student[] students) {
		for (Student student : students) {
			System.out.println("name=" + student.getName() + ",age=" + student.getAge());
		}
	}
}
