package com.sky.projects.design.behavior.strategy;

import com.sky.projects.design.behavior.strategy.impl.OperationAdd;
import com.sky.projects.design.behavior.strategy.impl.OperationMultiply;
import com.sky.projects.design.behavior.strategy.impl.OperationSubstract;

/**
 * 23. 策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改.<br>
 * 23.1 意图：定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。<br>
 * 23.2 主要解决：在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。<br>
 * 23.3 何时使用：一个系统有许多许多类，而区分它们的只是他们直接的行为。<br>
 * 23.4 如何解决：将这些算法封装成一个一个的类，任意地替换。<br>
 * 23.5 关键代码：实现同一个接口。<br>
 * 23.6 应用实例： 1、诸葛亮的锦囊妙计，每一个锦囊就是一个策略。 2、旅行的出游方式，选择骑自行车、坐汽车，每一种旅行方式都是一个策略。 3、JAVA
 * AWT 中的 LayoutManager。<br>
 * 23.7 优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。<br>
 * 23.8 缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。<br>
 * 23.9 使用场景： 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
 * 2、一个系统需要动态地在几种算法中选择一种。 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。<br>
 * 23.10 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。<br>
 * 
 * @author zealot
 *
 */
public class StatePatternDemo {

	public static void main(String[] args) {
		// 加法操作
		StrategyDelegate delegate = new StrategyDelegate(new OperationAdd());
		System.out.println("10 + 5 = " + delegate.operation(10, 5));

		// 减法操作
		delegate.setStrategy(new OperationSubstract());
		System.out.println("10 - 5 = " + delegate.operation(10, 5));

		// 乘法操作
		delegate.setStrategy(new OperationMultiply());
		System.out.println("10 * 5 = " + delegate.operation(10, 5));
	}
}
