package com.sky.projects.design.structural.proxy;

/**
 * 代理类: 与被代理类实现相同的接口且含有相同的参数
 * 
 * @author zealot
 *
 */
public class ProxyImage implements Image {

	// 持有被代理对象的引用
	private Image realImage;

	private String fileName;

	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 根据代理对象输出图像信息
	 */
	@Override
	public void display() {
		if (realImage == null) {// 创建代理对象不存在则创建
			realImage = new RealImage(fileName);
		}

		realImage.display();
	}
}