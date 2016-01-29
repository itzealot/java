package com.zt.design.behavior.state;

/**
 * Context 是一个带有某个状态的类
 * 
 * @author zengtao
 *
 */
public class Context {
	private State state;

	public Context() {
		state = null;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}
}
