package com.zt.design.behavior.interpreter;

/**
 * 16. 解释器模式（Interpreter Pattern）.<br />
 * 16.1 意图：给定一个语言，定义它的文法表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子。
 * 
 * 16.2 主要解决：对于一些固定文法构建一个解释句子的解释器。
 * 
 * 16.3 何时使用：如果一种特定类型的问题发生的频率足够高，那么可能就值得将该问题的各个实例表述为一个简单语言中的句子。这样就可以构建一个解释器，
 * 该解释器通过解释这些句子来解决该问题。
 * 
 * 16.4 如何解决：构件语法树，定义终结符与非终结符。
 * 
 * 16.5 关键代码：构件环境类，包含解释器之外的一些全局信息，一般是 HashMap。
 * 
 * 16.6 应用实例：编译器、运算表达式计算。
 * 
 * 16.7 优点： <br />
 * 1、可扩展性比较好，灵活。 <br />
 * 2、增加了新的解释表达式的方式。 3、易于实现简单文法。
 * 
 * 16.8 缺点： <br />
 * 1、可利用场景比较少。 <br />
 * 2、对于复杂的文法比较难维护。 <br />
 * 3、解释器模式会引起类膨胀。<br />
 * 4、解释器模式采用递归调用方法。
 * 
 * 16.9 使用场景：<br />
 * 1、可以将一个需要解释执行的语言中的句子表示为一个抽象语法树。 <br />
 * 2、一些重复出现的问题可以用一种简单的语言来进行表达。 <br />
 * 3、一个简单语法需要解释的场景。
 * 
 * 16.10 注意事项：可利用场景比较少，JAVA 中如果碰到可以用 expression4J 代替。
 * 
 * @author zengtao
 *
 */
public class InterpreterPatternDemo {

	// 规则：Robert 和 John 是男性
	public static Expression getMaleExpression() {
		Expression robert = new TerminalExpression("Robert");
		Expression john = new TerminalExpression("John");
		return new OrExpression(robert, john);
	}

	// 规则：Julie 是一个已婚的女性
	public static Expression getMarriedWomanExpression() {
		Expression julie = new TerminalExpression("Julie");
		Expression married = new TerminalExpression("Married");
		return new AndExpression(julie, married);
	}

	/**
	 * InterpreterPatternDemo 使用 Expression 类来创建规则，并解析它们
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Expression isMale = getMaleExpression();
		Expression isMarriedWoman = getMarriedWomanExpression();

		System.out.println("John is male? " + isMale.interpret("John"));
		System.out.println("Julie is a married women? "
				+ isMarriedWoman.interpret("Married Julie"));
	}
}