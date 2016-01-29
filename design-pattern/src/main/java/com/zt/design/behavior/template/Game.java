package com.zt.design.behavior.template;

/**
 * 创建一个抽象类，它的模板方法被设置为 final
 * 
 * @author zengtao
 *
 */
public abstract class Game {
	abstract void initialize();

	abstract void startPlay();

	abstract void endPlay();

	/**
	 * 模板方法，子类不能覆盖
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
