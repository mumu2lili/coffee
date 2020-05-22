package com.piggy.coffee.https.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.piggy.coffee.common.util.thread.ThreadUtils;

@EnableScheduling
@Component
public class ClearPodTask {
	private Logger log = LoggerFactory.getLogger(getClass());

	//@Scheduled(cron = "0/3 * * * * ?")
	public void work() throws Exception {
		log.info("clear ... " + System.currentTimeMillis());
		ThreadUtils.sleep(10000);
		throw new Exception("ex ..");
	}
}
