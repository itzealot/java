package com.zt.design.behavior.state;

/**
 * Context 是一个带有某个状态的类
 * 
 * @author zengtao
 *
 */
public class Context {

	// 状态
	private State state;

	public Context() {
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}
}
