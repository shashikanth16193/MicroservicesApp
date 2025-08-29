package com.myapp.course_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myapp.course_service.filter.JwtFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JwtFilter jwtFilter;
	 
	@Bean
	SecurityFilterChain chain(HttpSecurity http) throws Exception {
		
		http.csrf(cus->cus.disable())
		    .headers(headers -> headers
		            .frameOptions(frame -> frame.disable()) // ✅ correct way
		        )
				.authorizeHttpRequests(r->r.requestMatchers("/h2-console/**")
						                       .permitAll()
						                       .anyRequest().authenticated())
				
		    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
