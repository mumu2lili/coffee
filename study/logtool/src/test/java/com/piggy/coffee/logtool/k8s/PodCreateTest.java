package com.piggy.coffee.logtool.k8s;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodCreateTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static class TimePass {
		int hour;
		int minute;
		int second;
	}

	@Test
	public void testSort() throws IOException {

		List<String> list = new LinkedList<>();
		String logFile = null;
		logFile = "D:\\mumu\\company\\zhiqin_test\\特别监控\\create-20190929.txt";

		Files.lines(Paths.get(logFile), StandardCharsets.UTF_8).forEach(new Consumer<String>() {

			@Override
			public void accept(String line) {
				list.add(line);
			}
		});

		// 排序
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				TimePass timePass1 = parseTimePass(o1);
				TimePass timePass2 = parseTimePass(o2);

				if (timePass1.hour > timePass2.hour) {
					return -1;
				} else if (timePass1.hour < timePass2.hour) {
					return 1;
				}

				if (timePass1.minute > timePass2.minute) {
					return -1;
				} else if (timePass1.minute < timePass2.minute) {
					return 1;
				}

				if (timePass1.second > timePass2.second) {
					return -1;
				} else if (timePass1.second < timePass2.second) {
					return 1;
				}

				return 0;
			}

		});

		String recordStr = "2019-09-29 17:25:20";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		for (String line : list) {
			TimePass timePass = parseTimePass(line);
			LocalDateTime dateTime = LocalDateTime.parse(recordStr, dtf);
			dateTime = dateTime.plusHours(-timePass.hour);
			dateTime = dateTime.plusMinutes(-timePass.minute);
			dateTime = dateTime.plusSeconds(-timePass.second);
			
			dateTime = dateTime.plusMinutes(-6);
			log.info("时间[{}] 创建信息 [{}]", dateTime.format(dtf), line);
		}

	}

	private static TimePass parseTimePass(String line) {
		TimePass timePass = new TimePass();
		String pass = line.substring(0, line.indexOf(" "));

		int index = pass.indexOf("h");
		if (index > 0) {
			String tmp = pass.substring(0, index);
			timePass.hour = Integer.parseInt(tmp);
			if (index == pass.length()) {
				return timePass;
			}
			pass = pass.substring(index + 1);
		}

		index = pass.indexOf("m");
		if (index > 0) {
			String tmp = pass.substring(0, index);
			timePass.minute = Integer.parseInt(tmp);
			if (index == pass.length()) {
				return timePass;
			}
			pass = pass.substring(index + 1);
		}

		index = pass.indexOf("s");
		if (index > 0) {
			String tmp = pass.substring(0, index);
			timePass.second = Integer.parseInt(tmp);
		}

		return timePass;
	}

}
