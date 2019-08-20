package com.piggy.coffee.logtool.eva;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Consumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaExactSendTimeTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testExpendTime() throws IOException {

		Map<Integer, Integer> map = new TreeMap<>();

		String logFile = "D:\\\\tmp\\\\logs\\\\bridge.2019-08-03.log";
		logFile = "D:\\mumu\\company\\zhiqin\\test_data\\bridge02\\bridge.2019-08-20.log";
		Files.lines(Paths.get(logFile), StandardCharsets.UTF_8)
				.forEach(new Consumer<String>() {

					@Override
					public void accept(String line) {
						String str = "到kafka成功";
						if (line.matches(".*" + str + ".*")) {

							processSuccess(line, map);
						}
					}
				});

		log.info("out-success 耗时！！！！！！！");
		for (Iterator<Entry<Integer, Integer>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<Integer, Integer> entry = iter.next();

			if (entry.getValue() >= 1) {
				log.info(entry.getKey() + "数量 " + entry.getValue());
			}
		}
	}

	private void processSuccess(String line, Map<Integer, Integer> map) {

		String str = "out-success:";
		int index = line.indexOf(str) + str.length();
		String tmp = line.substring(index);
		int endIndex = tmp.indexOf("]");
		tmp = tmp.substring(1, endIndex);
		Integer expendTime = Integer.valueOf(tmp);

		Integer count = map.get(expendTime);

		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		map.put(expendTime, count);
	}

}
