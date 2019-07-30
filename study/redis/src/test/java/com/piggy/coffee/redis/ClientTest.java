package com.piggy.coffee.redis;

import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.Port;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piggy.coffee.redis.util.JedisUtil;
import com.piggy.coffee.redis.util.PortUtil;

public class ClientTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	private ConcurrentHashMap<Integer, Long> map = new ConcurrentHashMap<>();

	@Test
	public void test() {
		JedisUtil.set("port", "0");
		
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 50; j++) {
						int port = PortUtil.getPort3();
						log.info("线程{}获取到port {}", Thread.currentThread().getId(), port);
						map.put(port, Thread.currentThread().getId());
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}).start();
		}

		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("总共获取的port数量{}", map.size());
	}
	
	
	@Test
	public void test2() {
	    boolean r = PortUtil.isPortUsed("192.168.56.101", 3307);
	    System.out.println(r);
	}

}
