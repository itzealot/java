package com.sky.projects.guava;

import org.junit.Test;

import com.google.common.base.Enums;
import com.sky.projects.guava.entity.AuditStatus;

public class TestEnums {

	@Test
	public void getField() {
		System.out.println(Enums.getField(AuditStatus.MODIFY));
	}

}
