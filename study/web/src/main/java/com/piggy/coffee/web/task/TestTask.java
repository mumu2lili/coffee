package com.piggy.coffee.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Scheduled(cron = "0/5 * * * * ?")
	public void test() {
		log.info("测试定时任务...");
	}
}
