package com.sky.projects.jdk.lambda;

public interface IDefaulable {

	/**
	 * Interfaces now allow default methods, the implementer may or may not
	 * implement (override) them.
	 */
	default String notRequired() {
		return "Default implementation";
	}
}