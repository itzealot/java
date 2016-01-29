package com.zt.design.behavior.visitor;

/**
 * 定义一个表示元素的接口
 * 
 * @author zengtao
 *
 */
public interface ComputerPartVisitor {
	public void visit(Computer computer);

	public void visit(Mouse mouse);

	public void visit(Keyboard keyboard);

	public void visit(Monitor monitor);
}