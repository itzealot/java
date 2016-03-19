package com.zt.design.behavior.visitor;

/**
 * 监控
 * 
 * @author zt
 */
public class Monitor implements ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}