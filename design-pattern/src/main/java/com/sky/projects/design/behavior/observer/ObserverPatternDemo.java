package com.sky.projects.design.behavior.observer;

import com.sky.projects.design.behavior.observer.impl.BinaryObserver;
import com.sky.projects.design.behavior.observer.impl.HexaObserver;
import com.sky.projects.design.behavior.observer.impl.OctalObserver;

/**
 * 20. 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）.<br />
 * 20.1 意图：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 
 * 20.2 主要解决：一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。
 * 
 * 20.3 何时使用：一个对象（目标对象）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。
 * 
 * 20.4 如何解决：使用面向对象技术，可以将这种依赖关系弱化。
 * 
 * 20.5 关键代码：在抽象类里有一个 ArrayList 存放观察者们。
 * 
 * 20.6 应用实例： <br />
 * 1、拍卖的时候，拍卖师观察最高标价，然后通知给其他竞价者竞价。<br />
 * 2、西游记里面悟空请求菩萨降服红孩儿，菩萨洒了一地水招来一个老乌龟，这个乌龟就是观察者，他观察菩萨洒水这个动作。
 * 
 * 20.7 优点：<br />
 * 1、观察者和被观察者是抽象耦合的。 <br />
 * 2、建立一套触发机制。
 * 
 * 20.8 缺点：<br />
 * 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。<br />
 * 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。<br />
 * 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 * 
 * 20.9 使用场景：<br />
 * 1、有多个子类共有的方法，且逻辑相同。 <br />
 * 2、重要的、复杂的方法，可以考虑作为模板方法。
 * 
 * 20.10 注意事项：<br />
 * 1、JAVA 中已经有了对观察者模式的支持类。 <br />
 * 2、避免循环引用。 <br />
 * 3、如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。
 * 
 * @author zealot
 *
 */
public class ObserverPatternDemo {

	public static void main(String[] args) {
		// 使用 Subject 和实体观察者对象
		Subject subject = new Subject();

		// 16进制观察者
		subject.add(new HexaObserver());

		// 2进制观察者
		subject.add(new OctalObserver());

		// 16进制观察者
		subject.add(new BinaryObserver());

		subject.setState(15);
	}
}