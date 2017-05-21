package com.apusic.sicp.email;

import java.io.Serializable;

/**
 * email
 * 
 * @author zealot
 */
@SuppressWarnings("serial")
public class Email implements Serializable {

	/** 内容 */
	private String content;
	/** 信封 */
	private EmailEnvelope envelope;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EmailEnvelope getEnvelope() {
		return envelope;
	}

	public void setEnvelope(EmailEnvelope envelope) {
		this.envelope = envelope;
	}

}
