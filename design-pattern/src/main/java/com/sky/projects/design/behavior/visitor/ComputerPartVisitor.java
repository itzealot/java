package com.sky.projects.design.behavior.visitor;

import com.sky.projects.design.behavior.visitor.impl.Computer;
import com.sky.projects.design.behavior.visitor.impl.Keyboard;
import com.sky.projects.design.behavior.visitor.impl.Monitor;
import com.sky.projects.design.behavior.visitor.impl.Mouse;

/**
 * 定义一个表示元素的接口.<br />
 * 访问计算机的接口.<br />
 * 
 * @author zealot
 *
 */
public interface ComputerPartVisitor {

	/**
	 * 访问计算机
	 * 
	 * @param computer
	 */
	void visit(Computer computer);

	/**
	 * 访问鼠标
	 * 
	 * @param mouse
	 */
	void visit(Mouse mouse);

	/**
	 * 访问键盘
	 * 
	 * @param keyboard
	 */
	void visit(Keyboard keyboard);

	/**
	 * 访问监控器
	 * 
	 * @param monitor
	 */
	void visit(Monitor monitor);
}