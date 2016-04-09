package com.sky.projects.jdk.io.impl;

import java.util.List;

import com.sky.projects.jdk.io.AppendStrategy;
import com.sky.projects.jdk.io.Files;

public class AppendWithBufferWriter implements AppendStrategy {

	@Override
	public void write(String file, List<String> contents) {
		Files.appendWithBufferedWriter(file, contents);
	}

}
