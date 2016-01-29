package com.zt.design.behavior.visitor;

public interface ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor);
}
