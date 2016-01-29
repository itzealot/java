package com.zt.test;

public class Foo {

	public Foo() {
		super();
	}

	public Foo(int num) {
		super();
		this.num = num;
	}

	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Foo [num=" + num + "]";
	}
}