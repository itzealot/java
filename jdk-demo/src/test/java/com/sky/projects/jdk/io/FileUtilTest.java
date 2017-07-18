package com.sky.projects.jdk.io;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

public class FileUtilTest extends TestCase {

	public void testListFiles() {
		List<File> listFiles = Files.listFiles("E:\\projects", ".xml");

		if (listFiles != null)
			for (File f : listFiles) {
				System.out.println(f.getAbsolutePath());
			}
	}

}
