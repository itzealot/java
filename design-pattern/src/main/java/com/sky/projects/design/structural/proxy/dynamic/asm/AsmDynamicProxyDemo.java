package com.sky.projects.design.structural.proxy.dynamic.asm;

import com.sky.projects.design.structural.proxy.dynamic.javassist.Counter;
import com.sky.projects.design.structural.proxy.dynamic.javassist.CounterImpl;

public class AsmDynamicProxyDemo {

	public static void main(String[] args) throws Exception {
		System.out.println(AsmProxyFactory.createAsmBytecodeDynamicProxy(new CounterImpl(), Counter.class));
	}
}
