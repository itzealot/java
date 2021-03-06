package com.sky.projects.design.behavior.mediator;

/**
 * 18. 中介者模式（Mediator Pattern）.<br />
 * 18.1 意图：用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 * 
 * 18.2 主要解决：对象与对象之间存在大量的关联关系，这样势必会导致系统的结构变得很复杂，同时若一个对象发生改变，我们也需要跟踪与之相关联的对象，
 * 同时做出相应的处理 。
 * 
 * 18.3 何时使用：多个类相互耦合，形成了网状结构。
 * 
 * 18.4 如何解决：将上述网状结构分离为星型结构。
 * 
 * 18.5 关键代码：对象 Colleague 之间的通信封装到一个类中单独处理。
 * 
 * 18.6 应用实例： <br />
 * 1、中国加入 WTO 之前是各个国家相互贸易，结构复杂，现在是各个国家通过 WTO 来互相贸易。 <br />
 * 2、机场调度系统。 <br />
 * 3、MVC 框架，其中C（控制器）就是 M（模型）和 V（视图）的中介者。
 * 
 * 18.7 优点： <br />
 * 1、降低了类的复杂度，将一对多转化成了一对一。 <br />
 * 2、各个类之间的解耦。 <br />
 * 3、符合迪米特原则。
 * 
 * 18.8 缺点：中介者会庞大，变得复杂难以维护。
 * 
 * 18.9 使用场景：<br />
 * 1、系统中对象之间存在比较复杂的引用关系，导致它们之间的依赖关系结构混乱而且难以复用该对象。<br />
 * 2、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
 * 
 * 18.10 注意事项：不应当在职责混乱的时候使用。
 * 
 * @author zealot
 *
 */
public class MediatorPatternDemo {

	public static void main(String[] args) {
		User robert = new User("Robert");
		robert.sendMessage("Hi! John!");

		User john = new User("John");
		john.sendMessage("Hello! Robert!");
	}
}