package com.sky.projects.jdk.lambda.support;

/**
 * 接口可以继承接口。 如果父接口是一个函数接口， 那么子接口也可能是一个函数式接口。判断标准依据下面的条件：
 * 
 * 对于接口I, 假定M是接口成员里的所有抽象方法的继承(包括继承于父接口的方法)， 除去具有和Object的public的实例方法签名的方法，
 * 那么我们可以依据下面的条件判断一个接口是否是函数式接口， 这样可以更精确的定义函数式接口。 如果存在一个方法m， 满足：
 * m的签名（subsignature）是M中每一个方法签名的子签名（signature）
 * m的返回值类型是M中的每一个方法的返回值类型的替代类型（return-type-substitutable） 那么I就是一个函数式接口。
 * 
 * 
 * {@link http://blog.csdn.net/hainandao710/article/details/40589809}
 * 
 * @author zealot
 *
 */
public class InterfaceExtends {
	static class A {
		interface X {
			int m(Iterable<String> arg);
		}

		interface Y {
			int m(Iterable<String> arg);
		}

		/**
		 * 接口Z继承了X,Y接口的m方法，由于这两个方法的签名相同，返回值也一样，所以Z有唯一的一个抽象方法int
		 * m(Iterable<String> arg);,可以作为函数式接口。
		 */
		interface Z extends X, Y {
		}
	}

	@SuppressWarnings("rawtypes")
	static class B {
		interface X {
			Iterable m(Iterable<String> arg);
		}

		interface Y {
			Iterable<String> m(Iterable arg);
		}

		/**
		 * 方法签名Y.m既满足签名是X.m，并且返回值也满足return-type-substitutable。
		 * 所以Z是函数式接口，函数类型为Iterable<String> m(Iterable arg)。
		 */
		interface Z extends X, Y {
		}
	}

//	static class C {
//		interface X {
//			int m(Iterable<String> arg);
//		}
//
//		interface Y {
//			int m(Iterable<Integer> arg);
//		}
//
//		// 编译出错， 没有一个方法的签名是所有方法的子签名
//		interface Z extends X, Y {
//		}
//	}
}