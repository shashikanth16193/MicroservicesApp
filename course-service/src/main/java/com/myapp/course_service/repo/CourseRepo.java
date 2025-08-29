package com.myapp.course_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.course_service.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

	Course findByTitle(String title);

}
