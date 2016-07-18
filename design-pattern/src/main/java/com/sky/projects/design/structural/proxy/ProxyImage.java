package com.sky.projects.design.structural.proxy;

/**
 * 代理类: 与被代理类实现相同的接口且含有相同的参数
 * 
 * @author zengtao
 *
 */
public class ProxyImage implements Image {

	// 持有被代理对象的引用
	private RealImage realImage;

	private String fileName;

	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 根据代理对象输出图像信息
	 */
	public void display() {
		// 用于创建代理对象
		if (realImage == null) {
			realImage = new RealImage(fileName);
		}

		realImage.display();
	}
}