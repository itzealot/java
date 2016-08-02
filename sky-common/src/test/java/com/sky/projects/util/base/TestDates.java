package com.sky.projects.util.base;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.sky.projects.util.base.Dates;

public class TestDates {

	@Test
	public void after() {
		Assert.assertNotEquals("after some days error", null, Dates.after(new Date(), 2));
		Assert.assertNotEquals("after some days error", null, Dates.after(new Date(), -2));
		Assert.assertNotEquals("after some days error", null, Dates.after(new Date(), 20));
		Assert.assertNotEquals("after some days error", null, Dates.after(new Date(), -20));
	}

	@Test
	public void clearTime() {
		Date date = new Date();
		System.out.println(Dates.clearTime(date));
	}

	@Test
	public void yesterday() {
		System.out.println(Dates.yesterday());
	}

	@Test
	public void between() {
		System.out.println(Dates.between(new Date(), new Date()));
	}
}
