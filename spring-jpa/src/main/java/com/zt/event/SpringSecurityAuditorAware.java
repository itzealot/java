package com.zt.event;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	private String currentAuditor;

	@Override
	public String getCurrentAuditor() {
		return currentAuditor;
	}

	public void setCurrentAuditor(String currentAuditor) {
		this.currentAuditor = currentAuditor;
	}

}