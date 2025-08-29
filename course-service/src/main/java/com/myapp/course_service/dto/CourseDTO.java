package com.myapp.course_service.dto;

import lombok.Data;

@Data
public class CourseDTO {
	String title;
	Double price;
    Long teacherId;
    Double averageRating;

}
