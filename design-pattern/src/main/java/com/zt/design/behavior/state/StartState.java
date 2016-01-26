package com.zt.design.behavior.state;

/**
 * class StartState implements State.<br />
 * 开始状态实现类
 * 
 * @author zengtao
 *
 */
public class StartState implements State {
	/**
	 * 为 Context context 设置StartState对象
	 */
	public void doAction(Context context) {
		System.out.println("Player is in start state");

		// 设置Context context's state by this
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Start State";
	}
}