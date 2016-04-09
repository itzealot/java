package com.sky.projects.guava.entity;

public enum AuditStatus {
	MODIFY, PASS, NOT_PASS;

	public String label() {
		switch (this) {
		case MODIFY:
			return "MODIFY";
		case PASS:
			return "PASS";
		default:
			return "NOT_PASS";
		}
	}
}
