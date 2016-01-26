package com.zt.design.behavior.visitor;

/**
 * class Mouse implements ComputerPart
 * 
 * @author zengtao
 *
 */
public class Mouse implements ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}