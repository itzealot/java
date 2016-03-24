package com.zt.test.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.junit.Test;

public class TestIntBuffer {

	/**
	 * 测试 NIO中 Buffer 的变量：position, limit, capacity
	 * 
	 * Title: testVariable.<br />
	 * Description: .<br />
	 */
	@Test
	public void testVariable() {
		// 1. 通过 静态 allocate 方法开辟缓冲区,大小为 capacity
		int capacity = 10;
		IntBuffer buffer = IntBuffer.allocate(capacity);

		// 2. 输出缓冲区变量的信息
		display("初始化时变量值：", buffer);

		// 3. 使用put方法 向缓冲区写入数据，可以是单个，也可以是数组
		for (int i = 0; i < capacity; i++) {
			buffer.put(i);
		}

		// 4. 输出缓冲区变量的信息
		display("初始化后变量值：", buffer);

		// 5. 重置缓冲区 使用 flip 方法
		buffer.flip();

		// 6. 输出重置缓冲区后变量的信息
		display("重置缓冲区后变量值：", buffer);

		// 7. 输出缓冲区内容，必须先使用 flip 方法，利用 hasRemaining 方法与 get 方法实现缓冲区数据输出
		displayContent(buffer);
	}

	/**
	 * 测试子缓冲区，子缓冲区与父缓冲区共享
	 * 
	 * Title: testSubBuffer.<br />
	 * Description: .<br />
	 */
	@Test
	public void testSubBuffer() {
		// 1. 开辟并初始化缓冲区
		int capacity = 10;
		int limit = 8;
		IntBuffer buffer = IntBuffer.allocate(capacity);
		for (int i = 1; i <= limit; i++) {
			buffer.put(i);
		}
		buffer.flip();
		displayContent("初始化后缓冲区值：", buffer);

		// 2. 设置子缓冲区,通过设置 position 值与 limit 值，而后调用 slice 方法得到子缓冲区
		buffer.limit(5);
		buffer.position(2);
		IntBuffer subBuffer = buffer.slice();
		display("子缓冲区变量值：", buffer);

		// 3. 取出子缓冲区的内容，并修改
		for (int i = 0; i < subBuffer.capacity(); i++) {
			int temp = subBuffer.get(i);
			subBuffer.put(temp * 2);
		}

		// 4. 输出子缓冲区的内容
		subBuffer.flip();
		displayContent("子缓冲区内容： ", subBuffer);

		// 5. 输出缓冲区的内容，通过重置缓冲区与 设置 limit 大小
		buffer.flip();
		buffer.limit(limit);
		displayContent("修改后缓冲区内容：", buffer);
	}

	/**
	 * 只读缓冲区
	 * 
	 * Title: testReadOnlyBuffer.<br />
	 * Description: .<br />
	 */
	@Test
	public void testReadOnlyBuffer() {
		// 1. 准备出10个大小的缓冲区
		IntBuffer buffer = IntBuffer.allocate(10);
		IntBuffer read = null; // 定义子缓冲区
		for (int i = 0; i < 10; i++) {
			buffer.put(2 * i + 1); // 在主缓冲区中加入10个奇数
		}

		// 2. 创建只读缓冲区，不允许修改，要修改可以修改原始缓冲区
		read = buffer.asReadOnlyBuffer();

		// 3. 输出只读缓冲区
		read.flip();
		displayContent("只读缓冲区中的内容：", read);
		// read.put(30); // 修改，错误

		// 4. 修改原始缓冲区，只读缓冲区也会改变
		int element = buffer.get(4);
		buffer.put(4, element * 2);

		// 5. 输出只读缓冲区
		read.flip();
		displayContent("修改后只读缓冲区中的内容：", read);
	}

	/**
	 * 测试直接缓冲区，内存从物理内存上分配，不从虚拟机上分配
	 * 
	 * Title: testDirectBuffer.<br />
	 * Description: .<br />
	 */
	@Test
	public void testDirectBuffer() {
		// 准备出10个大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocateDirect(10);
		// 设置一组内容
		byte temp[] = { 1, 3, 5, 7, 9 };
		buf.put(temp);
		buf.flip();

		System.out.print("主缓冲区中的内容：");
		while (buf.hasRemaining()) {
			int x = buf.get();
			System.out.print(x + "、");
		}
	}

	/**
	 * To display buffer's content
	 * 
	 * Title: diplayContent.<br />
	 * Description: .<br />
	 * 
	 * @param buffer
	 */
	public void displayContent(String info, IntBuffer buffer) {
		System.out.print(info);
		displayContent(buffer);
	}

	/**
	 * To display buffer's content
	 * 
	 * Title: diplayContent.<br />
	 * Description: .<br />
	 * 
	 * @param buffer
	 */
	public void displayContent(IntBuffer buffer) {
		while (buffer.hasRemaining()) {
			System.out.print(buffer.get() + " ");
		}
		System.out.println();
	}

	/**
	 * 输出缓冲区Buffer 的变量：position, limit, capacity
	 * 
	 * Title: display.<br />
	 * Description: .<br />
	 * 
	 * @param buffer
	 */
	public void display(Buffer buffer) {
		System.out.println("position : " + buffer.position() + ", limit : "
				+ buffer.limit() + ", capacity : " + buffer.capacity());
	}

	/**
	 * 输出其他信息与缓冲区Buffer 的变量：position, limit, capacity
	 * 
	 * Title: display.<br />
	 * Description: .<br />
	 * 
	 * @param info
	 * @param buffer
	 */
	public void display(String info, Buffer buffer) {
		System.out.print(info);
		display(buffer);
	}
}
