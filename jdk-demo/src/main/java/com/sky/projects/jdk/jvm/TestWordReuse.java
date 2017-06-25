package com.sky.projects.jdk.jvm;

/**
 * 局部变量表中的字空间可以重用，使用 jclasslib 可以深入研究Class类文件的结构
 * 
 * 一个字为32位长度，对于long与double型的变量，占用两个字，其他类型使用一个字(如int,指针变量)
 * 
 * jvm: -XX:+PrintGCDetails
 * 
 * @author zealot
 */
@SuppressWarnings("unused")
public class TestWordReuse {

	public static void main(String[] args) {
		test61();
	}

	/**
	 * 变量a 的作用域只限于最近的大括号中，故在变量 b定义时，变量 a已经没有意义，故变量b完全可以重复用变量 a所在的空间，
	 * 其最大局部变量只需要3字(2+1),a与b复用，占一个字，外加this变量作为参赛，故最大局部变量表为13字
	 */
	public void test1() {
		{
			long a = 0;
		}

		long b = 0;
	}

	/**
	 * 定义了两个变量a与b，不存在重用的可能，其最大局部变量表容量需要5字(2+2+1)
	 */
	public void test2() {
		long a = 0;
		long b = 0;
	}

	/**
	 * 局部变量表的字，对系统GC也有一定影响。GC无法回收，因为b还在局部变量表中
	 */
	public static void test3() {
		{
			byte[] b = new byte[10 * 1024 * 1024];
		}

		System.gc();
		System.out.println("first explicit gc over.");
	}

	/**
	 * GC可回收，因为赋值为null将销毁局部变量表中的数据
	 */
	public static void test4() {
		{
			byte[] b = new byte[10 * 1024 * 1024];
			b = null;
		}

		System.gc();
		System.out.println("first explicit gc over.");
	}

	/**
	 * GC可回收，因为变量a复用了b的字，GC根无法找到b
	 */
	public static void test5() {
		{
			byte[] b = new byte[10 * 1024 * 1024];
		}

		int a = 0;
		System.gc();
		System.out.println("first explicit gc over.");
	}

	/**
	 * GC不可回收，因为变量a复用了c的字，b仍然存在
	 */
	public static void test6() {
		{
			int c = 0;
			byte[] b = new byte[10 * 1024 * 1024];
		}

		int a = 0; // 复用 c 的字
		System.gc();
		System.out.println("first explicit gc over.");
	}

	/**
	 * GC可回收，因为变量a复用了b的字，顺序复用
	 */
	public static void test61() {
		{
			byte[] b = new byte[10 * 1024 * 1024];
			int c = 0;
		}

		int a = 0; // 复用 b 的字
		System.gc();
		System.out.println("first explicit gc over.");
	}

	/**
	 * GC可回收，因为变量a复用了c的字，d复用b的字
	 */
	public static void test7() {
		{
			int c = 0;
			byte[] b = new byte[10 * 1024 * 1024];
		}

		int a = 0; // 复用 c 的字
		int d = 0; // 复用 b 的字
		System.gc();
		System.out.println("first explicit gc over.");
	}
}
