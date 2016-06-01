
package com.sky.projects.apache.common.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilsTest {

	public static void main(String[] args) throws IOException {
		File file = FileUtils.getFile("D:/logs");
		System.out.println(file.exists());

		// create file like touch cmd in unix
		File f = FileUtils.getFile("D:/logs/1.log");
		FileUtils.touch(new File(f.getParentFile(), f.getName() + ".ok"));
	}
}
