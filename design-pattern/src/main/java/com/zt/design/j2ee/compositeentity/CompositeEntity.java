package com.zt.design.j2ee.compositeentity;

/**
 * 创建组合实体
 * 
 * @author zengtao
 *
 */
public class CompositeEntity {
	private CoarseGrainedObject cgo = new CoarseGrainedObject();

	public void setData(String data1, String data2) {
		cgo.setData(data1, data2);
	}

	public String[] getData() {
		return cgo.getData();
	}
}
