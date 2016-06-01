package com.sky.projects.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public static void read(Path path) throws IOException {
		FileSystem hdfs = FileSystem.get(HDFSs.getConfig());

		FSDataInputStream fsDataInputStream = hdfs.open(path);

		IOUtils.copyBytes(fsDataInputStream, System.out, 4096, false);

		hdfs.close();
	}
}
