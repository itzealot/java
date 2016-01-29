package com.zt.design.structural.proxy;

/**
 * 真实的图像输出类且实现 Image 接口.<br />
 * 被代理的对象.<br />
 * 
 * @author zengtao
 *
 */
public class RealImage implements Image {
	// 文件名
	private String fileName;

	// 构造方法
	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	/**
	 * 输出图像信息
	 */
	public void display() {
		System.out.println("Displaying " + fileName);
	}

	/**
	 * 加载图像信息
	 * 
	 * @param fileName
	 */
	private void loadFromDisk(String fileName) {
		System.out.println("Loading " + fileName);
	}
}
