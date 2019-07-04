package com.piggy.coffee.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * Created by zzx on 17/10/24.
 */
public class JedisUtil {
	private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);

	/**
	 * 获取zset中元素个数
	 */
	public static int zlen(String key) {
		Jedis jedis = null;
		int len = 0;
		try {
			jedis = RedisPool.getJedis();
			len = jedis.zcard(key).intValue();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return len;
	}

	/**
	 * 获取元素在zset中的排名
	 */
	public static double zrank(String key, String value) {
		Jedis jedis = null;
		double rank = Double.MIN_VALUE;
		try {
			jedis = RedisPool.getJedis();
			if (jedis.zcard(key) == 0) {
				return rank;
			}
			rank = jedis.zrank(key, value);
		} catch (Exception e) {
			rank = Double.MAX_VALUE;
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return rank;
	}

	/**
	 * 将zset中队首元素弹出，若zset中没有元素，则返回null
	 */
	public static String zpop(String key) {
		Jedis jedis = null;
		String element = null;
		try {
			jedis = RedisPool.getJedis();
			if (jedis.zcard(key) != 0) {
				element = jedis.zrange(key, 0, 0).iterator().next();
				jedis.zrem(key, element);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return element;
	}

	/**
	 * 将一个新元素存到zset队尾，如果zset中尚没有元素，zadd(key, 1, value)
	 */
	public static void zpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			if (jedis.zcard(key) == 0) {
				jedis.zadd(key, 1, value);
			} else {
				String lastElement = jedis.zrange(key, -1, -1).iterator().next();
				double lastScore = jedis.zscore(key, lastElement);
				jedis.zadd(key, lastScore + 1, value);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * 将zset中指定元素score加1
	 *
	 * 如果zset中不存在该元素，则此操作等同于zadd(key, 1, value)
	 */
	public static void zup(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.zincrby(key, 1, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * 将zset中指定元素score减1
	 *
	 * 当分数小于1，代表这个元素代表的任务已完成，删除这个元素
	 */
	public static void zdown(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.zincrby(key, -1, value);
			if (jedis.zscore(key, value) < 1) {
				jedis.zrem(key, value);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * 看一个key中某value元素是否存在，若不存在，返回false
	 */
	public static boolean zhas(String key, String value) {
		Jedis jedis = null;
		boolean has = true;
		try {
			jedis = RedisPool.getJedis();
			has = jedis.zscore(key, value) != null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return has;
	}

	/**
	 * hash表，set一个键值对
	 */
	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * hash表，获取一个field的值
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = RedisPool.getJedis();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * hash表，删除一个field
	 */
	public static void hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.hdel(key, field);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * String: set
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * String: get
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = RedisPool.getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * String: delete
	 */
	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.del(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
	}

	/**
	 * String: get
	 */
	public static Long incrBy(String key, long increment) {
		Jedis jedis = null;
		Long value = null;
		try {
			jedis = RedisPool.getJedis();
			value = jedis.incrBy(key, increment);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			RedisPool.returnResource(jedis);
		}
		return value;
	}
}
