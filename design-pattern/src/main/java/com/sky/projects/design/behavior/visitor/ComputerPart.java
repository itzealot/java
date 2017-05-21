package com.sky.projects.design.behavior.visitor;

/**
 * 计算机组成部件接口
 * 
 * @author zealot
 */
public interface ComputerPart {

	/**
	 * 接收计算机的访问接口
	 * 
	 * @param vistor
	 */
	void accept(ComputerPartVisitor vistor);
}
