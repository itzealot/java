package com.sky.projects.jdk.jvm.params;

import java.util.Vector;

/**
 * 测试最大设置堆大小,-Xmx1M,-Xmx11M
 * 
 * @author zealot
 */
public class TestXmx {

	public static void main(String[] args) {
		try {
			Vector<byte[]> v = new Vector<>();
			for (int i = 0; i < 10; i++) {
				byte[] b = new byte[1024 * 1024];// allocate 1M

				v.add(b);// 强引用，使 GC时不能释放空间
				System.out.println("1M space is allocate");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// 获取系统可用的最大堆内存
			System.out.println("max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
		}
	}
}
