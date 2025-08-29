package com.myapp.api_gateway.filter;

import java.security.Key;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

public class JwtFilter implements GatewayFilter{

	@SuppressWarnings("null")
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		String SECRET="Sko4M2pzZGs4ZDJKU0QqMjhzZGYAbGtzZGpmeWV1OHhjblpMcTI5";
		
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
	
		
		
		String authHeader=exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
		String jwtToken=null;
				
				if(authHeader==null || !authHeader.startsWith("Bearer "))
				{
					System.out.println("-------NUll HEADER --------");
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					return exchange.getResponse().setComplete();
				}
				jwtToken=authHeader.substring(7);
				try {
					
				
					Jwts.parserBuilder()
				    .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
				    .build()
				    .parseClaimsJws(jwtToken);  // will throw ExpiredJwtException if expired no need to write manually

					System.out.println("-------Token veriefied--------");
				}
				catch(Exception e) {
					System.out.println("-------Got Exception--------");
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				 return exchange.getResponse().setComplete();
				}
				
		return chain.filter(exchange);
	}

}
