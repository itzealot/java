package com.sky.projects.guava;


import org.junit.Test;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Charsets;

public class TestCharsets {
	private String string = "I love you!";

	@Test
	public void testUTF_8() {
		byte[] data = string.getBytes(Charsets.UTF_8);
		display(data);
	}

	@Test
	public void testDisplay() {
		display(null);
	}

	public void display(byte[] data) {
		checkNotNull(data);
		int length = data.length;
		for (int i = 0; i < length; i++) {
			System.out.println(data[i]);
		}
	}
}
