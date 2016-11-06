package com.sky.projects.jdk.jvm;

/**
 * 永久区常量池的回收
 * 
 * jvm: -XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
 * 
 * @author zealot
 */
public class TestMethodInInternalGC {

	public static void main(String[] args) {
		finalConstGC();
	}

	/**
	 * intern: 如果常量池中存在当前String，则返回池中对象；如果不存在，则先加入常量池，并返回池中的对象引用
	 * 
	 * 当常量池饱和时，GC 总能顺利回收常量池数据，确保程序稳定继续运行
	 */
	public static void finalConstGC() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String t = String.valueOf(i).intern(); // 加入常量池
		}
	}
}
