package com.sky.projects.jdk.io;

import java.util.Arrays;

import com.sky.projects.jdk.io.impl.AppendWithBufferWriter;

public class TestAppendWithFileWriter {

	public static void main(String[] args) {

		AppendStrategy strategy = new AppendWithBufferWriter();

		Runnable thread = new AppendThread("Hello.txt", Arrays.asList("I am hello world!", "A World"), strategy);

		new Thread(thread).start();
	}
}