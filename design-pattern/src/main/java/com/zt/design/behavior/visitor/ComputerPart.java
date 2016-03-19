package com.zt.design.behavior.visitor;

/**
 * 计算机组成部件接口
 * 
 * @author zt
 */
public interface ComputerPart {

	/**
	 * 接收计算机的访问接口
	 * 
	 * @param computerPartVisitor
	 */
	public void accept(ComputerPartVisitor computerPartVisitor);
}
