package com.zt.design.creational.builder;

/**
 * 4. 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象。<br />
 * 根据相应形式创建相应的套餐。<br />
 * 
 * 4.1 意图：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 * 
 * 4.2 主要解决：主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；由于需求的变化，
 * 这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。
 * 
 * 4.3 何时使用：一些基本部件不会变，而其组合经常变化的时候。
 * 
 * 4.4 如何解决：将变与不变分离开。
 * 
 * 4.5 关键代码：建造者：创建和提供实例，导演：管理建造出来的实例的依赖关系。
 * 
 * 4.6 应用实例： <br />
 * 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 <br />
 * 2、JAVA 中的 StringBuilder。
 * 
 * 4.7 优点：<br />
 * 1、建造者独立，易扩展。 <br />
 * 2、便于控制细节风险。<br />
 * 
 * 4.8 缺点：<br />
 * 1、产品必须有共同点，范围有限制。 <br />
 * 2、如内部变化复杂，会有很多的建造类。<br />
 * 
 * 4.9 使用场景： <br />
 * 1、需要生成的对象具有复杂的内部结构。<br />
 * 2、需要生成的对象内部属性本身相互依赖。<br />
 * 
 * 4.10 注意事项：与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
 * 
 * @author zengtao
 *
 */
public class MealBuilder {

	/**
	 * 准备/创建 Veg套餐
	 * 
	 * @return
	 */
	public Meal prepareVegMeal() {
		Meal meal = new Meal();

		meal.addItem(new VegBurger());
		meal.addItem(new Coke());

		return meal;
	}

	/**
	 * 准备/创建 NonVeg套餐
	 * 
	 * @return
	 */
	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();

		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());

		return meal;
	}
}
