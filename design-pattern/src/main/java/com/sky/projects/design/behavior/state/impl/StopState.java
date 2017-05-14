package com.sky.projects.design.behavior.state.impl;

import com.sky.projects.design.behavior.state.Context;
import com.sky.projects.design.behavior.state.State;

/**
 * 终止状态
 * 
 * @author zealot
 */
public class StopState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("Player is in stop state");
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Stop State";
	}
}