package com.zt.design.behavior.memento;

/**
 * 创建 Memento 类(备忘录类)
 * 
 * @author zengtao
 *
 */
public class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
