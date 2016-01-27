package com.apusic.sicp.email;

import java.io.Serializable;

/**
 * Email 信封实体 EmailEnvelope
 * 
 * @author zt
 *
 */
public class EmailEnvelope implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3064551609288965986L;

	/**
	 * 收件人
	 */
	private String receiver;

	/**
	 * 发件人
	 */
	private String sender;

	/**
	 * 主题
	 */
	private String subject;

	/**
	 * 抄送
	 */
	private String cc;

	/**
	 * 暗送
	 */
	private String bcc;

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

}
