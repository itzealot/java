package com.sky.projects.jdk.util;

import java.io.File;

import junit.framework.TestCase;

public class FilesTest extends TestCase {

	public void testGenOkFile() {
		Files.genOkFile(new File("E:/test"));
	}

}
