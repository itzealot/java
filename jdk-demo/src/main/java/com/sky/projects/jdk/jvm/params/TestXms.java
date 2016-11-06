package com.sky.projects.jdk.jvm.params;

import java.util.Vector;

/**
 * 使用-Xms设置最小堆内存
 * 
 * jvm: -Xmx11M -Xms4M -verbose:gc or -Xmx11M -Xms11M -verbose:gc
 * 
 * @author zealot
 */
public class TestXms {
	public static void main(String[] args) {
		try {
			Vector<byte[]> v = new Vector<>();
			for (int i = 0; i <= 10; i++) {
				byte[] b = new byte[1024 * 1024];// allocate 1M

				v.add(b);// 强引用，使 GC时不能释放空间
				if (v.size() == 3) {
					v.clear();// 清空引用
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
