package com.zt.test.model;

import java.util.List;

/**
 * 使用Gson解析复杂的json数据<br />
 * 
 * 注意： <br />
 * 1、内部嵌套的类必须是static的，要不然解析会出错； <br />
 * 2、类里面的属性名必须跟Json字段里面的Key是一模一样的； <br />
 * 3、内部嵌套的用[]括起来的部分是一个List，所以定义为 public List<B> b，而只用{}嵌套的就定义为 public C c，
 * 
 * @author zt
 *
 */
public class JsonBean {
	private String a;
	private List<B> b;
	private C c;

	public static class C {
		private String c1;
		private String c2;

		public String getC1() {
			return c1;
		}

		public void setC1(String c1) {
			this.c1 = c1;
		}

		public String getC2() {
			return c2;
		}

		public void setC2(String c2) {
			this.c2 = c2;
		}

		@Override
		public String toString() {
			return "C [c1=" + c1 + ", c2=" + c2 + "]";
		}

	}

	public static class B {
		private String b1;
		private String b2;

		public String getB1() {
			return b1;
		}

		public void setB1(String b1) {
			this.b1 = b1;
		}

		public String getB2() {
			return b2;
		}

		public void setB2(String b2) {
			this.b2 = b2;
		}

		@Override
		public String toString() {
			return "B [b1=" + b1 + ", b2=" + b2 + "]";
		}

	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public List<B> getB() {
		return b;
	}

	public void setB(List<B> b) {
		this.b = b;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "JsonBean [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

}