package com.example.board.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	

	List<Review> findByRefBoardCodeAndReviewStatus(Long refBoardCode, String reviewStatus);

	Review findByReviewCode(Long reviewCode);


}
