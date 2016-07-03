package com.sky.projects.jdk.nio.flock;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Test;

import com.sky.projects.jdk.thread.Threads;

public class TestFileLock {

	@Test
	public void test() throws Exception {
		// 得到文件流
		File file = new File("d:" + File.separator + "out.txt");

		// 表示是追加操作
		FileOutputStream output = new FileOutputStream(file, true);

		// 2. 得到文件通道
		FileChannel fout = output.getChannel();

		// 3. 通过文件通道得到文件锁，此处为独占锁, 进行独占锁的操作
		FileLock lock = fout.tryLock();

		if (lock != null) {
			System.out.println(file.getName() + "文件锁定300秒");
			Threads.sleep(300000);
			lock.release(); // 释放
			System.out.println(file.getName() + "文件解除锁定。");
		}

		// 关闭文件通道与文件流
		fout.close();
		output.close();
	}
}
