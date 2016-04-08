package com.sky.projects.jdk.lambda;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	/**
	 * 1. 如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false。<br />
	 * 2. 为了防止Optional为空值， orElseGet()方法通过回调函数来产生一个默认值。<br />
	 * 3. map()函数对当前Optional的值进行转化，然后返回一个新的Optional实例。<br />
	 * 4. orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数。<br />
	 */
	@Test
	public void test1() {
		// is null
		Optional<String> fullName = Optional.ofNullable(null);
		// false
		System.out.println("Full Name is set: " + fullName.isPresent());

		// [none]
		System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));

		// Hey Stranger!
		System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
	}

	@Test
	public void test2() {
		// not null
		Optional<String> firstName = Optional.of("Tom");

		// true
		System.out.println("First Name is set: " + firstName.isPresent());

		// Tom
		System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));

		// Hey Tom!
		System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
	}
}
