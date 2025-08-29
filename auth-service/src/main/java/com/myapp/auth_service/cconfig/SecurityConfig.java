package com.myapp.auth_service.cconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomerUDS customerUDS;
	
	@Bean
	SecurityFilterChain myChain(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf -> csrf.disable())
        .headers(headers -> headers
            .frameOptions(frame -> frame.disable()) // ✅ correct way
        )
		.authorizeHttpRequests(r->r.requestMatchers("/myLogin/**","/h2-console/**","/register")
				                       .permitAll()
				                       .anyRequest().authenticated())
		     .formLogin(Customizer.withDefaults())
		     .httpBasic(Customizer.withDefaults())
		    
		     .sessionManagement(cus->cus.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(5);
	}
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(customerUDS);
		
		provider.setPasswordEncoder(encoder());
		
		return provider;
		
	}
	

}
