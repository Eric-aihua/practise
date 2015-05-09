package com.example.redis;

import redis.clients.jedis.Jedis;

public class GetRedisInstanceInfo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.11.20.140");  
	}

}
