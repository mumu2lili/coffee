package com.piggy.coffee.tmp.eva;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.junit.Test;

public class EvaLogFileTest2 {

	@Test
	public void testExpendTime() throws IOException {

		Map<String, Integer> map = new HashMap<>();

		Files.lines(Paths.get("C:\\Users\\mumu\\Desktop\\tmp\\bridge02\\bridge.2019-07-24.log"), StandardCharsets.UTF_8)
				.forEach(new Consumer<String>() {

					@Override
					public void accept(String line) {
						String str = "评测：tpiID:";
						if (line.matches(".*" + str + ".*")) {
							int index = line.indexOf(str) + str.length();
							String tpiId = line.substring(index);
							tpiId = tpiId.substring(0, tpiId.indexOf(",")).trim();
							String reqTime = line.substring(0, 14);

							process(reqTime, map);
						}
					}
				});

		System.out.println("严重超时！！！！！！！");
		for (Iterator<Entry<String, Integer>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Integer> entry = iter.next();

			if (entry.getValue() > 2) {
				System.out.println(entry.getKey() + "数量 " + entry.getValue());
			}
		}
	}

	private void process(String reqTime, Map<String, Integer> map) {
		Integer count = map.get(reqTime);
		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		map.put(reqTime, count);
	}

}
