package com.zt.test.api.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestNIOReader {

	@Test
	public void testReadFromFile() {
		FileInputStream inputStream = null;
		try {
			// 1. FileInputStream获取Channel(通道)
			inputStream = new FileInputStream(
					"C:\\Users\\zengtao\\Desktop\\Java网络编程\\test.txt");
			FileChannel channel = inputStream.getChannel();

			// 2. 建立缓冲区 Buffer
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			// 3. 数据从Channel读到Buffer
			int length = channel.read(buffer);
			System.out.println("byte's length = " + length);

			// 4. 读缓存
			buffer.flip();

			// 输出从缓存读到的数据
			while (buffer.hasRemaining()) {
				System.out.println((char) buffer.get());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWriteIntoFile() {
		FileOutputStream outputStream = null;
		try {
			// 1. FileOutputStream获取通道Channel
			outputStream = new FileOutputStream(
					"C:\\Users\\zengtao\\Desktop\\Java网络编程\\test.txt");
			FileChannel channel = outputStream.getChannel();

			// 2. 创建缓冲区且放数据
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			String message = "I love you!";
			byte[] data = message.getBytes();
			buffer.put(data);

			// 需要调用该方法，为数据到Channel做准备
			buffer.flip();

			// 3. 写入缓冲区
			channel.write(buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
