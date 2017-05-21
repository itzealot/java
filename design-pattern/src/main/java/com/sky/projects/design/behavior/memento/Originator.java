package com.sky.projects.design.behavior.memento;

/**
 * 原始对象，即需要备忘存储的对象(存储核心数据，如状态信息)
 * 
 * @author zealot
 */
public class Originator {
	// 状态
	private String state;

	/**
	 * 根据当前对象的状态创建备忘录对象
	 * 
	 * @return
	 */
	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	/**
	 * 获取备忘录对象的状态保存在当前对象并返回
	 * 
	 * @param memento
	 */
	public String getStateFromMemento(Memento memento) {
		this.state = memento.getState();
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}