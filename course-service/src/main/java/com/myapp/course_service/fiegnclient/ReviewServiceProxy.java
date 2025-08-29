package com.myapp.course_service.fiegnclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="REVIEW-SERVICE")
public interface ReviewServiceProxy {
	
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER','ADMIN'")
	@GetMapping("/reviews/course/{courseId}/average")
	Double averageRatingOfCourse(@PathVariable("courseId") Long courseId);

}
