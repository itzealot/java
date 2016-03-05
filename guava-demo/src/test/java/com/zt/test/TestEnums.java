package com.zt.test;

import org.junit.Test;

import com.google.common.base.Enums;
import com.zt.test.entity.AuditStatus;

public class TestEnums {

	@Test
	public void getField() {
		System.out.println(Enums.getField(AuditStatus.MODIFY));
	}

}
