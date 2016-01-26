package com.zt.design.behavior.visitor;

/**
 * class Computer implements ComputerPart
 * 
 * @author zengtao
 *
 */
public class Computer implements ComputerPart {

	ComputerPart[] parts;

	public Computer() {
		// 组成部分初始化
		parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
	}

	public void accept(ComputerPartVisitor computerPartVisitor) {
		/*
		 * 初始化必要的组件
		 */
		for (int i = 0; i < parts.length; i++) {
			parts[i].accept(computerPartVisitor);
		}

		// 初始化本身
		computerPartVisitor.visit(this);
	}
}