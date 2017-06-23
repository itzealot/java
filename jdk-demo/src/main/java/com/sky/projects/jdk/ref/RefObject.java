package com.sky.projects.jdk.ref;

public class RefObject {

	@Override
	protected void finalize() throws Throwable {
		// 内存回收时该方法仅仅会被调用一次
		super.finalize();
		System.out.println("finalize was called......");
	}

}
