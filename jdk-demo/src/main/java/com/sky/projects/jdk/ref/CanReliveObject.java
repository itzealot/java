package com.sky.projects.jdk.ref;

public class CanReliveObject {

	// 用于拯救对象
	private static CanReliveObject obj = null;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize call......");
		// 拯救将要被回收的对象
		obj = this;
	}

	@Override
	public String toString() {
		return "obj";
	}

	public static CanReliveObject getObj() {
		return obj;
	}

}
