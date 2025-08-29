package com.myapp.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallBack")
public class FallBackController {

	@GetMapping("/movieFallBack")
	String movieFallBack() {
		return "movieFallBack";
	}
	
	@GetMapping("/reviewFallBack")
	String reviewFallBack() {
		return "reviewFallBack";
	}
}
