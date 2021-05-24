package com.piggy.coffee.common.util.Date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

	public static Instant parseIsoTime(String time) {
		DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT;
		Instant instant = Instant.from(f.parse(time));
		return instant;

	}

	public static Date parseIsoTimeToDate(String time) {
		Instant instant = parseIsoTime(time);
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		return Date.from(zonedDateTime.toInstant());

	}
}
