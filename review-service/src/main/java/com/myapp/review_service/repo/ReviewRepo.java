package com.myapp.review_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.review_service.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

	List<Review> findAllByCourseId(Long courseId);

	List<Review> findAllByStudentId(Long studentId);

}
