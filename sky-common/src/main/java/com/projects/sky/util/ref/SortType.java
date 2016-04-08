package com.projects.sky.util.ref;

public enum SortType {
	ASC("asc"), // 升序
	DESC("desc"); // 降序

	private String label;

	private SortType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}