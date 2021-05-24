package com.piggy.coffee.common.util.date;

import java.time.Instant;
import java.util.Date;

import org.junit.Test;

import com.piggy.coffee.common.util.Date.DateUtils;

public class DateUtilsTest {

	@Test
	public void testParseIsoTime() {
		String s = "2021-05-24T02:23:00Z";
		Instant date = DateUtils.parseIsoTime(s);
		System.out.println(date);
	}

	@Test
	public void testParseIsoTimeToDate() {
		String s = "2021-05-24T02:23:00Z";
		Date date = DateUtils.parseIsoTimeToDate(s);
		System.out.println(date);
	}

}
