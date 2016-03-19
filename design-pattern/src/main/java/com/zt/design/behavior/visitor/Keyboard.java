package com.zt.design.behavior.visitor;

/**
 * 键盘
 * 
 * @author zengtao
 *
 */
public class Keyboard implements ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}