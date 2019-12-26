package com.piggy.coffee.logtool.eva;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaOvertimeTest2 {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static class Eva {
		public String secKey;
		public String evaStr;
		public String reqTime;
		public String sendStr;
		public String endTime;
	}

	@Test
	public void testExpendTime() throws IOException {

		Map<String, List<Eva>> map = new HashMap<>();
		String logFile = null;
		logFile = "D:\\mumu\\company\\zhiqin\\test_data\\bridge02\\bridge.2019-07-24.log";
		logFile = "D:\\mumu\\company\\zhiqin\\test_data\\u01\\bridge.2019-07-25.log";
		logFile = "D:\\\\tmp\\\\logs\\\\bridge.2019-08-03.log";
		Files.lines(Paths.get(logFile), StandardCharsets.UTF_8)
				.forEach(new Consumer<String>() {

					@Override
					public void accept(String line) {
						String str = "评测：tpiID:";
						if (line.matches(".*" + str + ".*")) {
							int index = line.indexOf(str) + str.length();
							String tpiId = line.substring(index);
							tpiId = tpiId.substring(0, tpiId.indexOf(",")).trim();

							Eva eva = parseEva(line);
							processEva(tpiId, eva, map);

						} else if (line.matches(".*" + "[end]评测：tpiID: " + ".*")) {
							processSendKafka(line, map);
						}

					}
				});

		log.info("严重超时！！！！！！！");
		for (Iterator<Entry<String, List<Eva>>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<String, List<Eva>> entry = iter.next();
			List<Eva> list = entry.getValue();
			for (Eva eva : list) {
				if (eva.sendStr == null) {
					continue;
				}
				log.error(eva.secKey + "  超时");
				log.error(eva.evaStr);
				log.error(eva.sendStr);
			}
		}
	}

	private void processSendKafka(String line, Map<String, List<Eva>> map) {
		String endTime = line.substring(0, 14);
		int endIndex = line.indexOf("]");
		String secKey = line.substring(16, endIndex);

		int beginIndex = line.indexOf("[end]评测：tpiID: ") + "[end]评测：tpiID: ".length();
		endIndex = line.indexOf(",  buildID");
		String tpiId = line.substring(beginIndex, endIndex);

		List<Eva> list = map.get(tpiId);
		if (list == null) {
			return;
		}
		for (Iterator<Eva> iter = list.iterator(); iter.hasNext();) {
			Eva eva = iter.next();
			if (eva.secKey.equals(secKey) && eva.endTime == null) {
				long diffSecond = diffSecond(eva.reqTime, endTime);
				if (diffSecond == 0) {
					iter.remove();
				} else if (diffSecond == 1) {
					iter.remove();
					System.out.println("可能相差1s------------------");
					System.out.println(eva.evaStr);
					System.out.println(line);
				} else {
					eva.sendStr = line;
					eva.endTime = endTime;
				}
				break;
			}
		}

	}

	private long diffSecond(String start, String end) {
		LocalDateTime s = LocalDateTime.parse("2019-" + start.replace(" ", "T"));
		LocalDateTime e = LocalDateTime.parse("2019-" + end.replace(" ", "T"));
		long expendTime = ChronoUnit.SECONDS.between(s, e);
		return expendTime;
	}

	private void processEva(String tpiId, Eva eva, Map<String, List<Eva>> map) {
		List<Eva> list = map.get(tpiId);
		if (list == null) {
			list = new LinkedList<>();
			map.put(tpiId, list);
		}
		list.add(eva);
	}

	private Eva parseEva(String line) {
		Eva eva = new Eva();
		eva.evaStr = line;
		eva.reqTime = line.substring(0, 14);
		int endIndex = line.indexOf("]");
		eva.secKey = line.substring(16, endIndex);
		return eva;
	}

}
