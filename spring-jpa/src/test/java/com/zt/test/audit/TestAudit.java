package com.zt.test.audit;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.event.SpringSecurityAuditorAware;
import com.zt.repository.AuditDepartmentRepository;

public class TestAudit {
	private ApplicationContext applicationContext = null;
	private SpringSecurityAuditorAware auditorAware = null;
	private AuditDepartmentRepository repository = null;

	{
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	@Test
	public void test() {
		auditorAware = (SpringSecurityAuditorAware) applicationContext
				.getBean("springSecurityAuditorAware");
		auditorAware.setCurrentAuditor("zhangsan");
		repository = applicationContext
				.getBean(AuditDepartmentRepository.class);
		auditorAware.setCurrentAuditor(repository.findOne(1L).getEmail());

		System.out.println(auditorAware.getCurrentAuditor());
	}
}
