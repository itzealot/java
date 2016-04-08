package com.sky.projects.jdk.nio.flock;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Test;

public class TestFileLock {
	@Test
	public void test() throws Exception {

		// 得到文件流
		File file = new File("d:" + File.separator + "out.txt");
		FileOutputStream output = null;

		// 表示是追加操作
		output = new FileOutputStream(file, true);

		// 2. 得到文件通道
		FileChannel fout = null;
		fout = output.getChannel();// 得到通道

		// 3. 通过文件通道得到文件锁，此处为独占锁
		FileLock lock = fout.tryLock(); // 进行独占锁的操作
		if (lock != null) {
			System.out.println(file.getName() + "文件锁定300秒");
			Thread.sleep(300000);
			lock.release(); // 释放
			System.out.println(file.getName() + "文件解除锁定。");
		}

		// 关闭文件通道与文件流
		fout.close();
		output.close();
	}
}