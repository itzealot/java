package com.sky.projects.design.behavior.visitor.impl;

import com.sky.projects.design.behavior.visitor.ComputerPart;
import com.sky.projects.design.behavior.visitor.ComputerPartVisitor;

/**
 * 计算机
 * 
 * @author zealot
 *
 */
public class Computer implements ComputerPart {

	// 计算机的组成部件
	ComputerPart[] parts;

	public Computer() {
		// 计算机组成部分初始化
		parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
	}

	@Override
	public void accept(ComputerPartVisitor visit) {
		// 初始化必要的组件
		for (int i = 0; i < parts.length; i++) {
			parts[i].accept(visit);
		}

		// 初始化本身
		visit.visit(this);
	}
}