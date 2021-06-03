package com.customerreply.model;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CustomerReplyDAO implements CustomerReplyDAO_interface {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	@Override
	public List<String> getHistoryMsg(Integer userID) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");
			// LINKED LIST  Q. 不確定聊天訊息要分左右的話應如何儲存 由C判斷?
			String key = userID.toString();
			List<String> historyMsg = jedis.lrange(key, 0, -1);
			return historyMsg;			
		}finally {
			jedis.close();
		}
	}

	@Override
	public void insertMsg(Integer userID, String Msg) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");
			jedis.rpush(userID.toString(), Msg);
		}finally {
			jedis.close();
		}	
		// Msg 目前預想放入一個 Json (不確定是否要NEW個物件)
		// {
		//	"type" : "1",
		// 	"msg" : "I am the one who knocks.",
		// }

	}

}
