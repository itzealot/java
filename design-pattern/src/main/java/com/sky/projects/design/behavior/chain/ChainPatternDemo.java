package com.sky.projects.design.behavior.chain;

import com.sky.projects.design.behavior.chain.impl.ConsoleLogger;
import com.sky.projects.design.behavior.chain.impl.ErrorLogger;
import com.sky.projects.design.behavior.chain.impl.FileLogger;

/**
 * 14. 责任链模式.<br />
 * 14.1 意图：避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。
 * 
 * 14.2 主要解决：职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递，
 * 所以职责链将请求的发送者和请求的处理者解耦了。
 * 
 * 14.3 何时使用：在处理消息的时候以过滤很多道。
 * 
 * 14.4 如何解决：拦截的类都实现统一接口。
 * 
 * 14.5 关键代码：Handler 里面聚合它自己，在 HanleRequest 里判断是否合适，如果没达到条件则向下传递，向谁传递之前 set 进去。
 * 
 * 14.6 应用实例：<br />
 * 1、红楼梦中的"击鼓传花"。 <br />
 * 2、JS 中的事件冒泡。 <br />
 * 3、JAVA WEB 中 Apache Tomcat 对 Encoding 的处理，Struts2 的拦截器，jsp servlet 的 Filter。
 * 
 * 14.7 优点： <br />
 * 1、降低耦合度。它将请求的发送者和接收者解耦。 <br />
 * 2、简化了对象。使得对象不需要知道链的结构。 <br />
 * 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。 <br />
 * 4、增加新的请求处理类很方便。
 * 
 * 14.8 缺点： <br />
 * 1、不能保证请求一定被接收。<br />
 * 2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。 <br />
 * 3、可能不容易观察运行时的特征，有碍于除错。
 * 
 * 14.9 使用场景：<br />
 * 1、有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。 <br />
 * 2、在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。 <br />
 * 3、可动态指定一组对象处理请求。
 * 
 * 14.10 注意事项：在 JAVA WEB 中遇到很多应用。
 * 
 * @author zealot
 *
 */
public class ChainPatternDemo {

	/**
	 * 获取日志链，返回日志链的根
	 * 
	 * @return
	 */
	private static AbstractLogger getChainOfLoggers() {
		// 根据不同日志级别创建不同类型的记录仪
		AbstractLogger errorLogger = new ErrorLogger(LoggerLevel.ERROR);
		AbstractLogger fileLogger = new FileLogger(LoggerLevel.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(LoggerLevel.INFO);

		// 设置日志链
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		// 返回日志链的根
		return errorLogger;
	}

	public static void main(String[] args) {
		// 日志链
		AbstractLogger loggerChain = getChainOfLoggers();

		loggerChain.logMessage(LoggerLevel.INFO, "This is an information.");

		System.out.println("-------------------------------------------------");

		loggerChain.logMessage(LoggerLevel.DEBUG, "This is an debug level information.");

		System.out.println("-------------------------------------------------");

		loggerChain.logMessage(LoggerLevel.ERROR, "This is an error information.");
	}
}
