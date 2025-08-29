package com.myapp.course_service.controller;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myapp.course_service.dto.CourseDTO;
import com.myapp.course_service.entity.Course;
import com.myapp.course_service.fiegnclient.ReviewServiceProxy;
import com.myapp.course_service.repo.CourseRepo;

@RestController
@RequestMapping("/course-service")
public class CourseController {
    
  
    @PreAuthorize("hasAuthority('STUDENT')")
	@GetMapping("/student")
	String student(){
		
		return "student";
		
	}
    @PreAuthorize("hasAuthority('TEACHER')")
	@GetMapping("/teacher")
	String teacher(){
		
		return "teacher";
		
	}
    @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin")
	String admin(){
		
		return "admin";
		
	}
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','STUDENT')")
	@GetMapping("/everyone")
	String everyone(){
		
		return "everyone";
		
	}
	
	/*
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ReviewServiceProxy reviewServiceProxy;
	
	@Autowired
	RestTemplate restTemplate;
	
    @PreAuthorize("hasAuthority('TEACHER')")
	@PostMapping("/courses")
	Course createCourse(@RequestBody CourseDTO courseDTO, Authentication authentication ) {
    	Integer customerId=(Integer) authentication.getDetails(); 
		
		Course c=Course.builder()
				            .title(courseDTO.getTitle())
				            .price(courseDTO.getPrice())
				            .teacherId(customerId).build();
				      
		return courseRepo.save(c);
	}
	
	
	@PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','STUDENT')")
	@GetMapping("/courses")
	List<Course> getAllCourses(){
		
		return courseRepo.findAll();
		
	}
	@PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','STUDENT')")
	@GetMapping("/courses/{courseId}")
	 Course getCourseById(@PathVariable("courseId") Long courseId){
		
		return courseRepo.findById(courseId).orElse(null);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN','TEACHER','STUDENT')")
	@GetMapping("/courses/{title}")
	 Course getCourseByTitle(@PathVariable("title") String title){
		
		return courseRepo.findByTitle(title);
	}
	
    @PreAuthorize("hasAuthority('ADMIN')")	    
	@DeleteMapping("/courses/{courseId}")
	  void deleteCourse(@PathVariable("courseId") Long courseId) {
		
		courseRepo.deleteById(courseId);
		
	}
	*/
	
	

	
	
	
	

}
