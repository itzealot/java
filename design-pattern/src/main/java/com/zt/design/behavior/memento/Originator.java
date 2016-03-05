package com.zt.design.behavior.memento;

/**
 * Originator 创建并在 Memento 对象中存储状态。
 * 
 * @author zengtao
 */
public class Originator {
	// 备忘录的状态
	private String state;

	/**
	 * 利用已设置在当前 Originator 对象中的状态新建备忘录对象，该对象具备原备忘录状态
	 * 
	 * @return
	 */
	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	/**
	 * 获取备忘录对象的状态保存在当前 Originator 对象中
	 * 
	 * @param Memento
	 */
	public void getStateFromMemento(Memento Memento) {
		state = Memento.getState();
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}