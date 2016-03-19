package com.zt.design.behavior.visitor;

/**
 * 定义一个表示元素的接口.<br />
 * 访问计算机的接口.<br />
 * 
 * @author zengtao
 *
 */
public interface ComputerPartVisitor {
	/**
	 * 访问计算机
	 * 
	 * @param computer
	 */
	public void visit(Computer computer);

	/**
	 * 访问鼠标
	 * 
	 * @param mouse
	 */
	public void visit(Mouse mouse);

	/**
	 * 访问键盘
	 * 
	 * @param keyboard
	 */
	public void visit(Keyboard keyboard);

	/**
	 * 访问监控器
	 * 
	 * @param monitor
	 */
	public void visit(Monitor monitor);
}