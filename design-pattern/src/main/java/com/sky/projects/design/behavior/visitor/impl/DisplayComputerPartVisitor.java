package com.sky.projects.design.behavior.visitor.impl;

import com.sky.projects.design.behavior.visitor.ComputerPartVisitor;

/**
 * 计算机访问实现类
 * 
 * @author zealot
 */
public class DisplayComputerPartVisitor implements ComputerPartVisitor {

	@Override
	public void visit(Computer computer) {
		System.out.println("display Computer.");
	}

	@Override
	public void visit(Mouse mouse) {
		System.out.println("display Mouse.");
	}

	@Override
	public void visit(Keyboard keyboard) {
		System.out.println("display Keyboard.");
	}

	@Override
	public void visit(Monitor monitor) {
		System.out.println("display Monitor.");
	}
}