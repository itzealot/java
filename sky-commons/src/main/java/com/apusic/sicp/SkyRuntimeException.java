package com.apusic.sicp;

@SuppressWarnings("serial")
public class SkyRuntimeException extends RuntimeException {

	public SkyRuntimeException() {
		super();
	}

	public SkyRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkyRuntimeException(String message) {
		super(message);
	}

	public SkyRuntimeException(Throwable cause) {
		super(cause);
	}

}
