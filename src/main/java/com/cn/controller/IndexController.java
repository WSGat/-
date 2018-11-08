package com.cn.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.service.RedisService;

@RestController
public class IndexController {
	
	@Resource(name = "redisService")
	private RedisService redisService;
	
	@RequestMapping("/setToken")
	public String setToken(String value){
		redisService.setString(UUID.randomUUID()+"", value);
		return "success";
	}
	
	@RequestMapping("/getToken")
	public String getToken(String key){
		
		return  (String) redisService.getValue(key);
	}
}
