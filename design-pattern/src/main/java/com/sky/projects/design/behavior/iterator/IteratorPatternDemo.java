package com.sky.projects.design.behavior.iterator;

import com.sky.projects.design.behavior.iterator.impl.NameRepository;

/**
 * 17. 迭代器模式（Iterator Pattern）.<br />
 * 17.1 意图：提供一种方法顺序访问一个聚合对象中各个元素, 而又无须暴露该对象的内部表示。
 * 
 * 17.2 主要解决：不同的方式来遍历整个整合对象。
 * 
 * 17.3 何时使用：遍历一个聚合对象。
 * 
 * 17.4 如何解决：把在元素之间游走的责任交给迭代器，而不是聚合对象。
 * 
 * 17.5 关键代码：定义接口：hasNext, next。
 * 
 * 17.6 应用实例：JAVA 中的 iterator。
 * 
 * 17.7 优点： <br />
 * 1、它支持以不同的方式遍历一个聚合对象。<br />
 * 2、迭代器简化了聚合类。 <br />
 * 3、在同一个聚合上可以有多个遍历。 <br />
 * 4、在迭代器模式中，增加新的聚合类和迭代器类都很方便，无须修改原有代码。
 * 
 * 17.8 缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，<br />
 * 这在一定程度上增加了系统的复杂性。
 * 
 * 17.9 使用场景： <br />
 * 1、访问一个聚合对象的内容而无须暴露它的内部表示。<br />
 * 2、需要为聚合对象提供多种遍历方式。<br />
 * 3、为遍历不同的聚合结构提供一个统一的接口。
 * 
 * 17.10 注意事项：迭代器模式就是分离了集合对象的遍历行为，抽象出一个迭代器类来负责，这样既可以做到不暴露集合的内部结构，又可让<br />
 * 外部代码透明地访问集合内部的数据
 * 
 * @author zealot
 */
public class IteratorPatternDemo {

	public static void main(String[] args) {
		// 使用 NameRepository 来获取迭代器，并打印名字
		NameRepository repository = new NameRepository();

		// 迭代输出
		for (Iterator<String> iter = repository.getIterator(); iter.hasNext();) {
			// 获取下一个元素
			String name = iter.next();

			System.out.println("Name : " + name);
		}
	}
}