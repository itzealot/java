package com.sky.projects.design.structural.proxy.dynamic.asm;

public class ByteArrayClassLoader extends ClassLoader {

	public ByteArrayClassLoader() {
		super(ByteArrayClassLoader.class.getClassLoader());
	}

	public synchronized Class<?> getClass(String name, byte[] code) {
		if (name == null) {
			throw new IllegalArgumentException("null");
		}
		return defineClass(name, code, 0, code.length);
	}

}