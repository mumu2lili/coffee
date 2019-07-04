package com.piggy.coffee.redis.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public final class RedisPool {
	private static final Logger log = LoggerFactory.getLogger(RedisPool.class);

	// 可用连接实例的最大数目，默认为8；
	// 如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
	private static Integer MAX_TOTAL = 1024;
	// 控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
	private static Integer MAX_IDLE = 200;
	// 等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
	// 如果超过等待时间，则直接抛出JedisConnectionException
	private static Integer MAX_WAIT_MILLIS = 10000;
	private static Integer TIMEOUT = 10000;
	// 在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
	// 如果为true，则得到的jedis实例均是可用的
	private static Boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool = null;

	// 静态块，初始化Redis连接池
	static {
		try {
			Properties prop = new Properties();
			String active = System.getProperty("spring.profiles.active");
			if(null == active) {
				active = "local";
			}
			prop.load(RedisPool.class.getClassLoader().getResourceAsStream("config_" + active + ".properties"));

			String ADDR = prop.getProperty("redisAddress");
			int PORT = Integer.parseInt(prop.getProperty("redisPort"));
			String AUTH = prop.getProperty("redisAuth");

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT_MILLIS);
			config.setTestOnBorrow(TEST_ON_BORROW);

			jedisPool = AUTH.equals("") ? new JedisPool(config, ADDR, PORT, TIMEOUT)
					: new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 获取Jedis实例
	 *
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		return jedisPool.getResource();
	}

	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			// jedis.close()取代jedisPool.returnResource(jedis)方法将3.0版本开始
			try {
				jedis.close();
			} catch (JedisException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}