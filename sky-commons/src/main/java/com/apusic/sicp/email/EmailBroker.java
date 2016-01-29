package com.apusic.sicp.email;

import com.apusic.sicp.SicpBusinessException;

/**
 * send Email by EmailBroker interface.
 * 
 * @author zt
 *
 */
public interface EmailBroker {

	/**
	 * To send email by Email object.
	 * 
	 * @param email
	 *            Email
	 * @throws SicpBusinessException
	 */
	public void sendEmail(Email email) throws SicpBusinessException;
}
