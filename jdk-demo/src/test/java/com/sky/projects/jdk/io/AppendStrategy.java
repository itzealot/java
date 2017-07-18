package com.sky.projects.jdk.io;

import java.util.List;

public interface AppendStrategy {

	void write(String file, List<String> contents);
}
