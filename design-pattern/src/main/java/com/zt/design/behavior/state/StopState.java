package com.zt.design.behavior.state;

public class StopState implements State {

	/**
	 * 为 Context context 设置StopState对象
	 */
	public void doAction(Context context) {
		System.out.println("Player is in stop state");

		// 设置Context context's state by this
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Stop State";
	}
}