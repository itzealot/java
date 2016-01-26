package com.zt.design.structural.proxy;

/**
 * 图像代理类；与被代理类实现相同的接口且含有相同的参数.<br />
 * ProxyImage implements Image.<br />
 * 
 * @author zengtao
 *
 */
public class ProxyImage implements Image {

	// 持有被代理对象的引用
	private RealImage realImage;
	private String fileName;

	/**
	 * 构造代理对象时出入相应参数
	 * 
	 * @param fileName
	 */
	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 根据代理对象输出图像信息
	 */
	public void display() {
		if (realImage == null) {
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}