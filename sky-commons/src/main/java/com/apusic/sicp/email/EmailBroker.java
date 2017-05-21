package com.apusic.sicp.email;

import com.apusic.sicp.SkyBusinessException;

/**
 * send Email by EmailBroker interface.
 * 
 * @author zealot
 *
 */
public interface EmailBroker {

	/**
	 * To send email by Email object.
	 * 
	 * @param email
	 *            Email
	 * @throws SkyBusinessException
	 */
	void sendEmail(Email email) throws SkyBusinessException;
}
