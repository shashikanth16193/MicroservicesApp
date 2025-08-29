package com.myapp.api_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RateLimiterConfig {

	@Bean
	RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(3, 10); 
	}
	
	
}
