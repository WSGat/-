package com.cn.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis demo
 * 
 * @author Administrator
 *
 */
@Service("redisService")
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	public void setString(String key, Object value){
		set(key, value, null);
	}
	
	public void setString(String key, Object value, Long timeout){
		set(key, value, timeout);
	}
	
	public void set(String key, Object value, Long timeout) {
		if (value == null) {
			return;
		}
		if (value instanceof String) {
			String strValue = (String) value;
			stringRedisTemplate.opsForValue().set(key, strValue);
			if (timeout != null) {
				stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
			}
		}
	}
	
	public Object getValue(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
}
