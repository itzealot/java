package com.zt.design.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 CareTaker 类, 对象负责从 Memento 中恢复对象的状态.<br />
 * 内含List<Memento> mementoList 以及对其进行添加与获取.<br />
 * 模拟栈的实现，先添加的在前面，后天加的在后面
 * 
 * @author zengtao
 *
 */
public class CareTaker {

	// The List<Memento>
	private List<Memento> mementoList = new ArrayList<Memento>();

	/**
	 * To add Memento object into List<Memento> mementoList
	 * 
	 * @param state
	 */
	public void add(Memento state) {
		mementoList.add(state);
	}

	/**
	 * To get the Memento from List<Memento> mementoList
	 * 
	 * @param index
	 * @return
	 */
	public Memento get(int index) {
		return mementoList.get(index);
	}
}
