package com.apusic.sicp;

public class SicpBusinessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1061926230813395271L;

	public SicpBusinessException() {
        super();
    }

    public SicpBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SicpBusinessException(String message) {
        super(message);
    }

    public SicpBusinessException(Throwable cause) {
        super(cause);
    }
}
