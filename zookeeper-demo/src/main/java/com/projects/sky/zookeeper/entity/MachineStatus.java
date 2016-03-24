package com.projects.sky.zookeeper.entity;

/**
 * 机器运行状态
 * 
 * @author zt
 * 
 */
public enum MachineStatus {

	RUNNING("运行中"), UNKNOWN("未知");

	private String label;

	private MachineStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
