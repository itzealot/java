package com.sky.projects.jdk.lambda;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 方法引用
 * 
 * @author zt
 *
 */
public class CarTest {
	private List<Car> cars;
	private Car car = null;

	{
		// 第一种方法引用是构造器引用，语法是Class::new，或者更一般的Class<T>::new
		car = Car.create(Car::new);
		cars = Arrays.asList(car);
	}

	@Test
	public void test1() {
		System.out.println(cars);
	}

	@Test
	public void test2() {
		// 第二种方法引用是静态方法引用，语法是Class::static_method。请注意这个方法接受一个Car类型的参数
		cars.forEach(Car::collide);
	}

	@Test
	public void test3() {
		// 第三种方法引用是特定类的任意对象的方法引用，语法是Class::method。请注意这个方法没有参数。
		cars.forEach(Car::repair);
	}

	@Test
	public void test4() {
		// 第四种方法引用是特定对象的方法引用，语法是instance::method。请注意，这个方法接受一个Car类型的参数
		cars.forEach(car::follow);
	}
}
