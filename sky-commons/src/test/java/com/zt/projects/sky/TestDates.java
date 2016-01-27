package com.zt.projects.sky;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.zt.projects.sky.util.Dates;

public class TestDates {

	@Test
	public void format() {
		Assert.assertNotEquals("date time format error.", "",
				Dates.format(new Date(), Dates.DateFormateType.DATE));
		Assert.assertNotEquals("date standard format error.", "",
				Dates.format(new Date(), Dates.DateFormateType.DATETIME));
		Assert.assertNotEquals("date most precise format error.", "",
				Dates.format(new Date(), Dates.DateFormateType.MOST_DATETIME));
	}

	@Test
	public void parse() {
		Date date = null;
		date = Dates.parse("2015-5-6", Dates.DateFormateType.DATE);
		Assert.assertNotEquals("date time parse error.", null, date);
		Assert.assertNotEquals("date time parse error.", null, Dates.parse(
				"2015-5-6 10:10:10", Dates.DateFormateType.DATETIME));
		Assert.assertNotEquals("date time parse error.", null, Dates.parse(
				"20150506101010100", Dates.DateFormateType.MOST_DATETIME));
	}

	@Test
	public void after() {
		Assert.assertNotEquals("after some days error", null,
				Dates.after(new Date(), 2));
		Assert.assertNotEquals("after some days error", null,
				Dates.after(new Date(), -2));
		Assert.assertNotEquals("after some days error", null,
				Dates.after(new Date(), 20));
		Assert.assertNotEquals("after some days error", null,
				Dates.after(new Date(), -20));
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
