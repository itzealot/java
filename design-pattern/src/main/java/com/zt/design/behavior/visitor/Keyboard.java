package com.zt.design.behavior.visitor;

/**
 * class Keyboard implements ComputerPart
 * 
 * @author zengtao
 *
 */
public class Keyboard implements ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}