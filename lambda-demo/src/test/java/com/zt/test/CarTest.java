package com.zt.test;

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
		car = Car.create(Car::new);
		cars = Arrays.asList(car);
	}

	/**
	 * 1. 第一种方法引用是构造器引用，语法是Class::new，或者更一般的Class<T>::new
	 */
	@Test
	public void test1() {
		System.out.println(cars);
	}

	/**
	 * 2. 第二种方法引用是静态方法引用，语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
	 */
	@Test
	public void test2() {
		cars.forEach(Car::collide);
	}

	/**
	 * 3. 第三种方法引用是特定类的任意对象的方法引用，语法是Class::method。请注意，这个方法没有参数。
	 */
	@Test
	public void test3() {
		cars.forEach(Car::repair);
	}

	/**
	 * 4 .第四种方法引用是特定对象的方法引用，语法是instance::method。请注意，这个方法接受一个Car类型的参数
	 */
	@Test
	public void test4() {
		cars.forEach(car::follow);
	}
}
