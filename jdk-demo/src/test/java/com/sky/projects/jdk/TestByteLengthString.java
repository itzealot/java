package com.sky.projects.jdk;

import org.junit.Test;

public class TestByteLengthString {
	@Test
	public void testGetStringByByteLength() {
		System.out.println(ByteLengthString.getStringByByteLength("我我ABC", 3));
		System.out.println(ByteLengthString.getStringByByteLength("我我ABC", 4));
		System.out.println(ByteLengthString.getStringByByteLength("我我ABC", 5));
		System.out.println(ByteLengthString.getStringByByteLength("我我ABC", 6));
		System.out.println(ByteLengthString.getStringByByteLength("我ABC", 3));
		System.out.println(ByteLengthString.getStringByByteLength("我ABC", 0));
		System.out.println(ByteLengthString.getStringByByteLength("我ABC", 1));
		System.out.println(ByteLengthString.getStringByByteLength("ABC", 1));
	}

	@Test
	public void testPrint() {
		ByteLengthString.print("我是AABBCC");
	}
}
