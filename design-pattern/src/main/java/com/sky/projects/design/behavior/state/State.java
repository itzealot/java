package com.sky.projects.design.behavior.state;

/**
 * 状态变换的接口
 * 
 * @author zealot
 */
public interface State {

	/**
	 * 接收 Context 对象，执行相应的操作，如变更 Context 中的状态的引用
	 * 
	 * @param context
	 */
	void doAction(Context context);
}