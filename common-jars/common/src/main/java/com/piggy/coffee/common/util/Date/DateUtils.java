package com.piggy.coffee.common.util.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

	public static LocalDateTime parseIsoTime(String time) {
		LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ISO_INSTANT);
		return date;

	}
}
