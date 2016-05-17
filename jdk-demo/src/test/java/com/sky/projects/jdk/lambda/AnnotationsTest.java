package com.sky.projects.jdk.lambda;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.sky.projects.jdk.lambda.Annotations.Holder;
import com.sky.projects.jdk.lambda.Annotations.NonEmpty;

public class AnnotationsTest {

	@Test
	public void test() {
		final Holder<String> holder = new @NonEmpty Holder<String>();
		System.out.println(holder);

		@NonEmpty
		Collection<@NonEmpty String> strings = new ArrayList<>();
		System.out.println(strings);
	}
}
