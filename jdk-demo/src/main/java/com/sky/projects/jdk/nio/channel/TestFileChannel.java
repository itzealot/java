package com.sky.projects.jdk.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestFileChannel {

	@Test
	public void testReadOf() throws IOException {
		readOf("d:/nio-data.txt");
	}

	public static void readOf(String filePath) throws IOException {
		File file = new File(filePath);

		FileInputStream input = new FileInputStream(file);

		// 获取文件通道
		FileChannel channel = input.getChannel();

		// 开辟缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 通过 channel 读数据到缓冲区
		while (channel.read(buffer) != -1) {
			// 刷新 缓冲区
			buffer.flip();

			while (buffer.hasRemaining()) {
				// 读取数据
				System.out.print((char) buffer.get());
			}

			// 清空 buffer
			buffer.clear();
		}

		input.close();
	}

	/**
	 * Scattering Reads是指数据从一个channel读取到多个buffer中。
	 * 
	 * buffer在数组中的顺序将从channel中读取的数据写入到buffer，当一个buffer被写满后，
	 * channel紧接着向另一个buffer中写。
	 * 
	 * Scattering Writes不能处理动态消息。
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static ByteBuffer[] scatter(String filePath) throws IOException {
		File file = new File(filePath);

		FileInputStream input = new FileInputStream(file);

		// 获取文件通道
		FileChannel channel = input.getChannel();

		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);

		ByteBuffer[] results = { header, body };

		channel.read(results);

		input.close();

		return results;
	}

	/**
	 * Gathering Writes是指数据从多个 buffer 写入到同一个channel。
	 * 
	 * 按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入。
	 * 
	 * Gathering Writes能较好的处理动态消息
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static ByteBuffer[] gather(String filePath) throws IOException {
		File file = new File(filePath);

		FileInputStream input = new FileInputStream(file);

		// 获取文件通道
		FileChannel channel = input.getChannel();

		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);

		// write data into buffers
		ByteBuffer[] results = { header, body };

		channel.write(results);

		input.close();

		return results;
	}

	/**
	 * 通道操作与缓冲区为主
	 * 
	 * @param args
	 * @throws Exception
	 */
	@Test
	public void testWrite() throws Exception {
		String info[] = { "MLDN", "MLDNJAVA", "www.mldn.cn", "www.mldnjava.cn", "李兴华", "lixinghua" };

		// 1. 新建文件与文件流对象(文件输出流，因为是向文件中写入数据)
		File file = new File("d:" + File.separator + "out.txt");
		FileOutputStream output = null;
		output = new FileOutputStream(file);

		// 2. 根据文件流对象得到输出的通道
		FileChannel fout = null;
		fout = output.getChannel();

		// 3. 开辟缓冲区，数据写入缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		for (int i = 0; i < info.length; i++) {
			buf.put(info[i].getBytes()); // 字符串变为字节数组放进缓冲区之中
		}

		// 4. 刷新缓冲区，通过文件通道将缓冲区内容写入文件
		buf.flip();
		fout.write(buf);

		// 关闭文件流
		fout.close();
		output.close();
	}

	@Test
	public void testNioCopyFile() throws Exception {
		// 1. 得到文件输入流与文件输出流
		File fileIn = new File("d:" + File.separator + "out.txt");
		File fileOut = new File("d:" + File.separator + "outnote.txt");

		FileInputStream input = new FileInputStream(fileIn);
		FileOutputStream output = new FileOutputStream(fileOut);

		// 2. 根据文件流得到文件输入通道与文件输出通道
		FileChannel fout = null;
		FileChannel fin = null;
		fout = output.getChannel();
		fin = input.getChannel();

		// 3. 创建缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);

		// 4. 文件输入、输出通道与缓冲区交流，进行数据的读写
		while (fin.read(buf) != -1) {
			buf.flip();
			fout.write(buf);
			buf.clear(); // 清空缓冲区,所有的状态变量的位置恢复到原点
		}

		// 5. 关闭流与通道
		fin.close();
		fout.close();
		input.close();
		output.close();
	}

	/**
	 * 测试 MappedByteBuffer
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMappedByteBuffer() throws Exception {
		// 1. 得到文件流
		File file = new File("d:" + File.separator + "out.txt");
		FileInputStream input = new FileInputStream(file);

		// 2. 得到文件通道
		FileChannel fin = input.getChannel();

		// 3. 得到 MappedByteBuffer 对象
		MappedByteBuffer mbb = fin.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

		// 4. 开辟空间接收内容
		byte data[] = new byte[(int) file.length()];
		int foot = 0;
		while (mbb.hasRemaining()) {
			data[foot++] = mbb.get(); // 读取数据
		}

		System.out.println(new String(data)); // 输出内容

		// 5. 关闭通道与流
		fin.close();
		input.close();
	}
}