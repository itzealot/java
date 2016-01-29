package com.apusic.sicp;

/**
 * 
 * @author zt
 *
 */
public class SicpRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2103307021573286840L;

	public SicpRuntimeException() {
		super();
	}

	public SicpRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SicpRuntimeException(String message) {
		super(message);
	}

	public SicpRuntimeException(Throwable cause) {
		super(cause);
	}

}
