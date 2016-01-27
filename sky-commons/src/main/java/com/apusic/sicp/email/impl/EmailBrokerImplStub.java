package com.apusic.sicp.email.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.apusic.sicp.SicpBusinessException;
import com.apusic.sicp.email.Email;
import com.apusic.sicp.email.EmailBroker;

/**
 * 
 * 
 * @author zt
 *
 */
public class EmailBrokerImplStub implements EmailBroker {
	private static final Log log = LogFactory.getLog(EmailBrokerImplStub.class);

	@Override
	public void sendEmail(Email email) throws SicpBusinessException {
		if (email == null) {
			log.debug("email is null!");
		} else {
			log.debug("email sender: " + email.getEnvelope().getSender());
			log.debug("email receiver: " + email.getEnvelope().getReceiver());
			log.debug("email subject: " + email.getEnvelope().getSubject());
			log.debug("email cc: " + email.getEnvelope().getCc());
			log.debug("email bcc: " + email.getEnvelope().getBcc());
			log.debug("email content: " + email.getContent());
		}
	}

}
