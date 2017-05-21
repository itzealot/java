package com.sky.projects.design.structural.proxy;

/**
 * 被代理的对象：真实的图像输出类且实现 Image 接口
 * 
 * @author zealot
 *
 */
public class RealImage implements Image {

	// 文件名
	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;

		// 根据文件名加载图像信息
		loadFromDisk(fileName);
	}

	@Override
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
