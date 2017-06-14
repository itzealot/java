package com.sky.projects.jdk.java8.function;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LambdaTest extends TestCase {
	public LambdaTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(LambdaTest.class);
	}

	public void testFunction() {
		Function<String, Integer> func = new Function<String, Integer>() {
			@Override
			public Integer apply(String t) {
				return Integer.parseInt(t);
			}
		};
		System.out.println(func.apply("1100"));

		// identity
		System.out.println(Function.identity());

		Function<Integer, Float> after = new Function<Integer, Float>() {
			@Override
			public Float apply(Integer t) {
				return t.floatValue();
			}

			@Override
			public <V> Function<Integer, V> andThen(Function<? super Float, ? extends V> after) {
				return Function.super.andThen(after);
			}
		};

		// andThen
		// Function<T1, T2>.andThen(Function<T2, T3>)=>Function<T1, T3>
		System.out.println(func.andThen(after).apply("100"));

		// compose
		// Function<T1, T2>.andThen(Function<T3, T1>)=>Function<T3, T2>
		// Function<Float, Integer>
		System.out.println(func.compose(new Function<Float, String>() {
			@Override
			public String apply(Float t) {
				return String.valueOf(t.intValue());
			}
		}).apply(15F));
	}

	public void testLambda() throws InterruptedException {
		new Thread(() -> System.out.println("Hello World!")).start();
		TimeUnit.SECONDS.sleep(1);

		String[] datas = new String[] { "peng", "zhao", "li" };
		Arrays.sort(datas, (v1, v2) -> Integer.compare(v1.length(), v2.length()));

		Stream.of(datas).forEach(param -> System.out.println(param));
	}

	/**
	 * lambda function
	 */
	public void testAcceptable() {
		Acceptable<String> acceptable = messaege -> System.out.println(messaege);

		accept(acceptable, "message");
	}

	/**
	 * lambda function with 2 params
	 */
	public void testAcceptable2() {
		Acceptable2<String, Long> acceptable = (messaege, value) -> System.out
				.println("msg=" + messaege + ", val=" + value);
		accept(acceptable, "messaege", 2L);
	}

	public <T> void accept(Acceptable<T> acceptable, T message) {
		acceptable.accept(message);
	}

	public <T1, T2> void accept(Acceptable2<T1, T2> acceptable, T1 t1, T2 t2) {
		acceptable.accept(t1, t2);
	}

	public void testMyFunction() {
		MyFunction function = () -> System.out.println("start run");
		accept(function);
	}

	public void accept(MyFunction function) {
		function.run();
		System.out.println(function.toString());
	}
}
