package com.sky.projects.jdk.design;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = -4637936215978116142L;

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

}
