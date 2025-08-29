package com.myapp.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter,Ordered{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		System.out.println("-------PRE-PROCESSING--------");
		long startTime = System.currentTimeMillis();

		//modify headers here it will happen after down stream call only use before commit()
	    return chain.filter(exchange)
	    	.then(Mono.fromRunnable(() -> {
	    	System.out.println("-------POST-PROCESSING--------");
	        long duration = System.currentTimeMillis() - startTime;
	       // exchange.getResponse().getHeaders().add("My-Response-Time", duration + "ms");
	      // we cant modify response in then cause response is committed it we should do before return
	    }));
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
