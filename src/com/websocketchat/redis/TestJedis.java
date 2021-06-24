package com.websocketchat.redis;

import redis.clients.jedis.JedisPool;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestJedis {
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
//			jedis.auth("123456");
			System.out.println(jedis.ping());
		}finally {
			
			jedis.close();
		}
//		CustomerReplyDAO dao = new CustomerReplyDAO();	// getHistoryMsg & insertMsg TEST
////		dao.insertMsg(0001, "test");
////		dao.insertMsg(0001, "123");
//		List<String> list = dao.getHistoryMsg(0001);
//		for(String msg : list)
//			System.out.println(msg);
		
	}

	
	
	

}
