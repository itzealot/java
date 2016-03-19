package com.zt.design.behavior.visitor;

/**
 * 鼠标
 * 
 * @author zengtao
 *
 */
public class Mouse implements ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}