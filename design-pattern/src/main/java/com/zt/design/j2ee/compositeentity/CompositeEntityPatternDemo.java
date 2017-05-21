package com.zt.design.j2ee.compositeentity;

/**
 * 组合实体模式（Composite Entity Pattern）用在 EJB 持久化机制中。一个组合实体是一个 EJB 实体
 * bean，代表了对象的图解。当更新一个组合实体时，内部依赖对象 beans 会自动更新，因为它们是由 EJB 实体 bean 管理的。以下是组合实体
 * bean 的参与者。
 * 
 * 组合实体（Composite Entity） - 它是主要的实体 bean。它可以是粗粒的，或者可以包含一个粗粒度对象，用于持续生命周期。
 * 粗粒度对象（Coarse-Grained Object） - 该对象包含以来对象。它有自己的生命周期，也能管理依赖对象的生命周期。
 * 依赖对象（Dependent Object） - 依赖对象是一个持续生命周期依赖于粗粒度对象的对象。 策略（Strategies） -
 * 策略表示如何实现组合实体。
 * 
 * @author zealot
 *
 */
public class CompositeEntityPatternDemo {

	public static void main(String[] args) {
		// 使用 Client 来演示组合实体设计模式的用法

		Client client = new Client();
		client.setData("Test", "Data");
		client.printData();

		client.setData("Second Test", "Data1");
		client.printData();
	}
}
