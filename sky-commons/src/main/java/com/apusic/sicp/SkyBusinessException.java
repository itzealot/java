package com.apusic.sicp;

@SuppressWarnings("serial")
public class SkyBusinessException extends RuntimeException {

	public SkyBusinessException() {
		super();
	}

	public SkyBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkyBusinessException(String message) {
		super(message);
	}

	public SkyBusinessException(Throwable cause) {
		super(cause);
	}
}
