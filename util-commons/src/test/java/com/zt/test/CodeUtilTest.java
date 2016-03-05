package com.zt.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CodeUtilTest {
	private String orgCode = "100001";

	@Test
	public void generateNodeCode() {
		assertTrue("node code not equal", CodeUtil.generateNodeCode(orgCode).equals("100001"));
	}

	@Test
	public void generateServiceCode() {
		assertTrue("service code not equal", CodeUtil.generateServiceCode(orgCode, 1L).equals("100001-N-S-00001"));
	}

	@Test
	public void generateMachineCode() {
		assertTrue("service code not equal", CodeUtil.generateMachineCode(orgCode, 1L).equals("100001-N-M-00001"));
	}

	@Test
	public void generateConsumerCode() {
		assertTrue("service code not equal", CodeUtil.generateConsumerCode(orgCode, 1L).equals("100001-R-00001"));
	}

	@Test
	public void getOrgCodeFrom() {
		assertTrue("org code not equal", "10001".equals(CodeUtil.getOrgCodeFrom("10001-N-S-00001")));
		assertTrue("org code not equal", "10001".equals(CodeUtil.getOrgCodeFrom("10001-N-M-00001")));
		assertTrue("org code not equal", "10001".equals(CodeUtil.getOrgCodeFrom("10001-N")));
		assertTrue("org code not equal", "Q1000100001".equals(CodeUtil.getOrgCodeFrom("Q1000100001")));
		assertTrue("org code not equal", "10001".equals(CodeUtil.getOrgCodeFrom("10001-R-00001")));
	}

	@Test
	public void getIdForm() {
		assertTrue("id not equal", CodeUtil.getIdForm("10001-N-S-00001").equals(1L));
		assertTrue("id not equal", CodeUtil.getIdForm("10001-N-M-10001").equals(10001L));
		assertTrue("id not equal", CodeUtil.getIdForm("10001-N").equals(0L));
		assertTrue("id not equal", CodeUtil.getIdForm("10001-R-02001").equals(2001L));
		assertTrue("id not equal", CodeUtil.getIdForm("1000100001").equals(0L));
	}

}
