package com.zt.design.behavior.state;

/**
 * 状态变换的接口
 * 
 * @author zengtao
 *
 */
public interface State {
	/**
	 * 为Context设置状态
	 * 
	 * @param context
	 */
	public void doAction(Context context);
}