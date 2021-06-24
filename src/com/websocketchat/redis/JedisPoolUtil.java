package com.websocketchat.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool = null;	// jedis 連線池設定，多人連線

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPool() {
		if (pool == null) {
			synchronized (JedisPoolUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(8);	// 連線數可以改大一點
					config.setMaxIdle(8);
					config.setMaxWaitMillis(10000);
//					pool = new JedisPool(config, "katyjava.mooo.com", 6379);	// GCP docker
					pool = new JedisPool(config, "127.0.0.1", 6379);	// 本機
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的contextDestroyed裡呼叫此方法註銷Redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}
