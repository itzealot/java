package com.zt.design.structural.decorator;

/**
 * 10. 装饰模型.<br />
 * 10.1 意图：动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
 * 
 * 10.2 主要解决：一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀。
 * 
 * 10.3 何时使用：在不想增加很多子类的情况下扩展类。
 * 
 * 10.4 如何解决：将具体功能职责划分，同时继承装饰者模式。
 * 
 * 10.5 关键代码：<br />
 * 1、Component 类充当抽象角色，不应该具体实现。<br />
 * 2、修饰类引用和继承 Component 类，具体扩展类重写父类方法。
 * 
 * 10.6 应用实例：<br />
 * 1、孙悟空有 72 变，当他变成"庙宇"后，他的根本还是一只猴子，但是他又有了庙宇的功能。<br />
 * 2、不论一幅画有没有画框都可以挂在墙上，但是通常都是有画框的
 * ，并且实际上是画框被挂在墙上。在挂在墙上之前，画可以被蒙上玻璃，装到框子里；这时画、玻璃和画框形成了一个物体。
 * 
 * 10.7 优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
 * 
 * 10.8 缺点：多层装饰比较复杂。
 * 
 * 10.9 使用场景： <br />
 * 1、扩展一个类的功能。 <br />
 * 2、动态增加功能，动态撤销。
 * 
 * 10.10 注意事项：可代替继承。
 * 
 * @author zengtao
 *
 */
public class DecoratorPatternDemo {

	public static void main(String[] args) {
		// 创建无修饰圆形
		Shape circle = new Circle();

		// 根据圆形创建有红色修饰圆形
		Shape redCircle = new RedShapeDecorator(new Circle());

		// 根据矩形创建有红色修饰矩阵
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		// 红颜色修饰圆形
		System.out.println("\nCircle of red border");
		redCircle.draw();

		// 红颜色修饰矩形
		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}