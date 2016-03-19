package com.zt.design.behavior.visitor;

/**
 * 计算机
 * 
 * @author zengtao
 *
 */
public class Computer implements ComputerPart {

	// 计算机的组成部件
	ComputerPart[] parts;

	public Computer() {
		// 计算机组成部分初始化
		parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
	}

	public void accept(ComputerPartVisitor computerPartVisitor) {
		// 初始化必要的组件
		for (int i = 0; i < parts.length; i++) {
			parts[i].accept(computerPartVisitor);
		}

		// 初始化本身
		computerPartVisitor.visit(this);
	}
}