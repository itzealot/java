package com.sky.projects.design.behavior.template;

/**
 * 游戏抽象类，提供了玩游戏的通用抽象方法供子类实现.<br />
 * 提供了一个游戏的启动方法，即模板方法.<br />
 * 其模板方法被设置为 final，子类不能覆盖.<br />
 * 
 * @author zealot
 *
 */
public abstract class Game {

	/**
	 * 初始化方法
	 */
	protected abstract void initialize();

	/**
	 * 开始方法
	 */
	protected abstract void startPlay();

	/**
	 * 结束方法
	 */
	protected abstract void endPlay();

	/**
	 * 模板方法，使用 final 修饰，子类不能覆盖.<br />
	 * 游戏规则通用方法.<br />
	 */
	public final void play() {
		// 初始化游戏
		initialize();

		// 开始游戏
		startPlay();

		// 结束游戏
		endPlay();
	}
}
