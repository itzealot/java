package com.sky.projects.design.structural.composite;

/**
 * 9. 组合模式.<br />
 * 9.1 意图：将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
 * 
 * 9.2 主要解决：它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，客户程序可以向处理简单元素一样来处理复杂元素，
 * 从而使得客户程序与复杂元素的内部结构解耦 。
 * 
 * 9.3 何时使用：<br />
 * 1、您想表示对象的部分-整体层次结构（树形结构）。 <br />
 * 2、您希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
 * 
 * 9.4 如何解决：树枝和叶子实现统一接口，树枝内部组合该接口。
 * 
 * 9.5 关键代码：树枝内部组合该接口，并且含有内部属性 List，里面放 Component。
 * 
 * 9.6 应用实例： <br />
 * 1、算术表达式包括操作数、操作符和另一个操作数，其中，另一个操作符也可以是操作树、操作符和另一个操作数。<br />
 * 2、在 JAVA AWT 和 SWING 中，对于 Button 和 Checkbox 是树叶，Container 是树枝。
 * 
 * 9.7 优点：<br />
 * 1、高层模块调用简单。<br />
 * 2、节点自由增加。
 * 
 * 9.8 缺点：在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
 * 
 * 9.9 使用场景：部分、整体场景，如树形菜单，文件、文件夹的管理。
 * 
 * 9.10 注意事项：定义时为具体类。
 * 
 * @author zealot
 *
 */
public class CompositePatternDemo {

	public static void main(String[] args) {
		Employee ceo = new Employee("John", "CEO", 30000);

		Employee headSales = new Employee("Robert", "Head Sales", 20000);

		Employee headMarketing = new Employee("Michel", "Head Marketing", 20000);

		Employee clerk1 = new Employee("Laura", "Marketing", 10000);
		Employee clerk2 = new Employee("Bob", "Marketing", 10000);

		Employee salesExecutive1 = new Employee("Richard", "Sales", 10000);
		Employee salesExecutive2 = new Employee("Rob", "Sales", 10000);

		ceo.add(headSales);
		ceo.add(headMarketing);

		headSales.add(salesExecutive1);
		headSales.add(salesExecutive2);

		headMarketing.add(clerk1);
		headMarketing.add(clerk2);

		System.out.println(ceo);

		for (Employee headEmployee : ceo.getSubordinates()) {
			System.out.println("\t" + headEmployee);

			for (Employee employee : headEmployee.getSubordinates()) {
				System.out.println("\t\t" + employee);
			}
		}
	}
}