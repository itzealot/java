package com.sky.projects.design.behavior.template;

/**
 * 游戏抽象类，提供了玩游戏的通用抽象方法供子类实现.<br />
 * 提供了一个游戏的启动方法，即模板方法.<br />
 * 其模板方法被设置为 final，子类不能覆盖.<br />
 * 
 * @author zealot
 */
public abstract class Game {

	/**
	 * 模板方法，使用 final 修饰，子类不能覆盖
	 */
	public final void play() {
		initialize();

		start();

		destroy();
	}

	// 以下均为通用方法，不同的实现类可以有不同的抽象

	/**
	 * 初始化方法
	 */
	protected abstract void initialize();

	/**
	 * 开始方法
	 */
	protected abstract void start();

	/**
	 * 结束方法
	 */
	protected abstract void destroy();
}
