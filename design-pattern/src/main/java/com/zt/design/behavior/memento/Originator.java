package com.zt.design.behavior.memento;

/**
 * Originator 创建并在 Memento 对象中存储状态。
 * 
 * @author zengtao
 *
 */
public class Originator {
	private String state;

	/**
	 * To save the state into Memento Object by String state
	 * 
	 * @return
	 */
	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	/**
	 * To get the state from Memento into this String state
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