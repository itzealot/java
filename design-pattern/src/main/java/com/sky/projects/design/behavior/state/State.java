package com.sky.projects.design.behavior.state;

/**
 * 状态变换的接口
 * 
 * @author zengtao
 *
 */
public interface State {

	/**
	 * 变更Context的状态
	 * 
	 * @param context
	 */
	public void doAction(Context context);
}