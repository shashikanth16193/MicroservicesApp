package com.myapp.review_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myapp.review_service.dto.ReviewDTO;
import com.myapp.review_service.entity.Review;
import com.myapp.review_service.repo.ReviewRepo;

@RestController
@RequestMapping("/review-service")
public class ReviewController {


	
	@PreAuthorize("hasAuthority('STUDENT')")
	@GetMapping("/student")
	String student()
	{
		return "student";
	}

	@PreAuthorize("hasAuthority('TEACHER')")
	@GetMapping("/teacher")
	String teacher() {
		return "teacher";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin")
	String admin() {
		return "admin";
	}
	
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER','ADMIN')")
	@GetMapping("/everyone")
	String everyone() {
		return "everyone";
	}
	
	/*
	@Autowired
	ReviewRepo reviewRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	//check weather course exist before adding review get course using rest template
	 
	@PreAuthorize("hasAuthority('STUDENT')")
	@PostMapping("/reviews")
	String addReview(@RequestBody ReviewDTO reviewDTO) {
		
		String exchangeUrl="lb://COURSE-SERVICE/courses/{courseId}";
	ResponseEntity<Map> course=	restTemplate.exchange(exchangeUrl,
				HttpMethod.GET,
				null,
				Map.class);
		if(course.getBody()!=null) {
		
		Review review=Review.builder()
				              .courseId(reviewDTO.getCourseId())
				              .studentId(reviewDTO.getStudentId())
				              .comment(reviewDTO.getComment())
				              .rating(reviewDTO.getRating()).build();
		reviewRepo.save(review);
	       return	"saved";
		}
		return "course doent exist";
	}
	
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER','ADMIN')")
	@GetMapping("/reviews/course/{courseId}")
	List<Review> getReviewsOfCourse(@PathVariable("courseId") Long courseId){
		
		return reviewRepo.findAllByCourseId(courseId);
		
	}
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@GetMapping("/reviews/student/{studentId}")
	List<Review> getReviewsOfStudent(@PathVariable("studentId") Long studentId){
		
		return reviewRepo.findAllByStudentId(studentId);
	}
	
	@PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
	@DeleteMapping("/reviews/{reviewId}")
	void deleteReview(@PathVariable("reviewId") Long reviewId) {
		
		reviewRepo.deleteById(reviewId);
	}
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER','ADMIN'")
	@GetMapping("/reviews/course/{courseId}/average")
	Double averageRatingOfCourse(@PathVariable("courseId") Long courseId) {
		
		List<Review> reviewsOfCourse=reviewRepo.findAllByCourseId(courseId);
		Double sum=reviewsOfCourse.stream().mapToDouble(Review::getRating).sum();
		return sum/reviewsOfCourse.size();
	}
	*/
	
		
}
