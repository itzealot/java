package com.zt.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.zt.test.Annotations.Holder;
import com.zt.test.Annotations.NonEmpty;

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
