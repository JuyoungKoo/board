package com.example.board.dto;

import lombok.Data;

@Data
public class ReviewDto {
	
	private Long reviewCode;
	
	private String reviewContent;
	
	private java.sql.Date reviewRegistDate;
	
	private String reviewStatus;
	
	private Long refBoardCode;
	

}
