package com.zt.design.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录列表
 * 
 * @author zengtao
 *
 */
public class CareTaker {

	// 备忘录列表
	private List<Memento> mementos = new ArrayList<Memento>();

	/**
	 * 添加备忘录对象到备忘录列表
	 * 
	 * @param state
	 */
	public void add(Memento state) {
		mementos.add(state);
	}

	/**
	 * 根据索引值从备忘录列表中获取备忘录对象
	 * 
	 * @param index
	 * @return
	 */
	public Memento get(int index) {
		if (index < 0 || index > mementos.size()) {
			throw new IllegalArgumentException("index value is out of bound where index = " + index);
		}

		return mementos.get(index);
	}
}
