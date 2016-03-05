package com.zt.design.behavior.memento;

/**
 * 创建备忘录类 Memento
 * 
 * @author zengtao
 */
public class Memento {
	// 备忘录的状态
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
