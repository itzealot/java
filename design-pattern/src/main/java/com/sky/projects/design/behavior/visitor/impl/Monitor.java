package com.sky.projects.design.behavior.visitor.impl;

import com.sky.projects.design.behavior.visitor.ComputerPart;
import com.sky.projects.design.behavior.visitor.ComputerPartVisitor;

/**
 * 监控
 * 
 * @author zealot
 */
public class Monitor implements ComputerPart {

	@Override
	public void accept(ComputerPartVisitor visitor) {
		visitor.visit(this);
	}
}