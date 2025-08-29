package com.myapp.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.myapp.api_gateway.filter.JwtFilter;

@Configuration
public class GatewayConfig {
	
	@Autowired
	RedisRateLimiter redisRateLimiter;
	
	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		
	return 	routeLocatorBuilder.routes()
		             .route("courseServiceId",r->r.path("/course-service/**")
		            		                       .filters(f->f.filter(new JwtFilter())
//		            		                    		        .circuitBreaker(config->
//		            		                    		             config.setName("GatewayCircuitBreaker")
//		            		                    		                   .setFallbackUri("forward:/fallBack/movieFallBack"))
//		            		                    		        .requestRateLimiter(config->
//		            		                    		              config.setRateLimiter(redisRateLimiter)
//		            		                    		                    .setStatusCode(HttpStatus.TOO_MANY_REQUESTS))
		            		                    		    )
		            		                      .uri("lb://COURSE-SERVICE")) 
		             .route("reviewServiceId",r->r.path("/review-service/**")
		            		                      .filters(f->f.filter(new JwtFilter())
//		            		                    		       .circuitBreaker(config->
//		            		                    		               config.setName("GatewayCircuitBreaker")
//		            		                    		                     .setFallbackUri("forward:/fallBack/reviewFallBack"))
//		            		                    		       .requestRateLimiter(config->
//		            		                    		               config.setRateLimiter(redisRateLimiter)
//		            		                    		                     .setStatusCode(HttpStatus.TOO_MANY_REQUESTS))
		            		                    		   )
		            		                       .uri("lb://REVIEW-SERVICE"))
		             .build();
		             
	}

}
