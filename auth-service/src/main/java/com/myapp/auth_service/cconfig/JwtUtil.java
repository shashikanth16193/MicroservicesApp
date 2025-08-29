package com.myapp.auth_service.cconfig;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	String SECRET="Sko4M2pzZGs4ZDJKU0QqMjhzZGYAbGtzZGpmeWV1OHhjblpMcTI5";
	Date cd=new Date();
	Date ed=new Date(cd.getTime()+60*10*1000);
	
	public String genearteJwt(String email, Collection<? extends GrantedAuthority> authorities, Integer id) {
		
		Map<String, Object> claims=new HashMap<>();
		claims.put("sub", email);
		claims.put("customerId", id);
		claims.put("iat",cd);
		claims.put("exp", ed);
		 List<String> roles = authorities.stream()
		            .map(GrantedAuthority::getAuthority)
		            .collect(Collectors.toList());

		claims.put("roles", roles);
		
	return Jwts.builder().setClaims(claims).signWith(getKey()).compact();
		
	}

	private Key getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
