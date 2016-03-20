package com.zt.design.creational.prototype;

/**
 * 抽象的图像类
 * 
 * @author zengtao
 *
 */
public abstract class Shape implements Cloneable {

	private String id;
	protected String type;

	/**
	 * 绘制图形
	 */
	abstract void draw();

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 克隆方法进行克隆，返回克隆对象
	 */
	@Override
	public Object clone() {
		Object clone = null;

		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clone;
	}
}