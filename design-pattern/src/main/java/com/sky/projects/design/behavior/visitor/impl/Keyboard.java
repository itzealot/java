package com.sky.projects.design.behavior.visitor.impl;

import com.sky.projects.design.behavior.visitor.ComputerPart;
import com.sky.projects.design.behavior.visitor.ComputerPartVisitor;

/**
 * 键盘
 * 
 * @author zealot
 */
public class Keyboard implements ComputerPart {

	@Override
	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}