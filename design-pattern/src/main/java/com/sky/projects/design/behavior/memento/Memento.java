package com.sky.projects.design.behavior.memento;

/**
 * 备忘录类(备份存储原始对象的状态)
 * 
 * @author zealot
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
