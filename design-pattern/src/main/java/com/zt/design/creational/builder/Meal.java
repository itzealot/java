package com.zt.design.creational.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个 Meal 类，带有上面定义的 Item 对象
 * 
 * @author zengtao
 *
 */
public class Meal {
	/**
	 * 多项Item
	 */
	private List<Item> items = new ArrayList<Item>();

	/**
	 * 添加Item项
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * 获得总钱数
	 * 
	 * @return
	 */
	public float getCost() {
		float cost = 0.0f;
		for (Item item : items) {
			cost += item.price();
		}
		return cost;
	}

	/**
	 * 显示所有Item项
	 */
	public void showItems() {
		for (Item item : items) {
			System.out.print("Item : " + item.name());
			System.out.print(", Packing : " + item.packing().pack());
			System.out.println(", Price : " + item.price());
		}
	}
}
