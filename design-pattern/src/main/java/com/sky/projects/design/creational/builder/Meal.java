package com.sky.projects.design.creational.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Meal 类
 * 
 * @author zealot
 *
 */
public class Meal {

	// 多项Item
	private List<Item> items = new ArrayList<>();

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
