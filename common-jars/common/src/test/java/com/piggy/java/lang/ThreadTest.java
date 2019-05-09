package com.piggy.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class ThreadTest {

	static {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		try {
			configurator.doConfigure("src/test/resources/logback-spring.xml");
		} catch (JoranException e) {
			throw new RuntimeException(e);
		}
	}

	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				log.info("start");
				throw new RuntimeException();
			}
		});

		MyUncaughtExceptionHandler eh = new MyUncaughtExceptionHandler();
		t.setUncaughtExceptionHandler(eh);

		t.start();

		while (t.isAlive()) {

		}
	}

	private static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
		private Logger log = LoggerFactory.getLogger(getClass());

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			log.error("exxxx", e);

		}

	}

}
