package com.myapp.review_service.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String SECRET="Sko4M2pzZGs4ZDJKU0QqMjhzZGYAbGtzZGpmeWV1OHhjblpMcTI5";
		String jwtToken=request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
		
		
		 byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		
		Claims claims=Jwts
				.parserBuilder()	
				.setSigningKey(Keys.hmacShaKeyFor(keyBytes))
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();
		String sub=claims.get("sub",String.class);
		List<String> roles=claims.get("roles",List.class);
		
	  List<SimpleGrantedAuthority> authorities=	roles.stream().map(r->new SimpleGrantedAuthority(r)).collect(Collectors.toList());
				
	       UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(sub,null, authorities);

	       SecurityContextHolder.getContext().setAuthentication(authToken);
	       filterChain.doFilter(request, response);
	}

}
