package com.zt.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.zt.event.SpringSecurityAuditorAware;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DataBaseConfig {

	@Bean(name = "auditorAware")
	public AuditorAware<String> auditoryProvider() {
		return new SpringSecurityAuditorAware();
	}
}