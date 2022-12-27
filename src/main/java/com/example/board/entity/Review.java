package com.example.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name ="review_tbl")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REVIEW_CODE")
	private Long reviewCode;
	
	@Column(name="REVIEW_CONTENT")
	private String reviewContent;
	
	@Column(name="REVIEW_REGISTDATE")
	private java.sql.Date reviewRegistDate;
	
	@Column(name="REVIEW_STATUS")
	private String reviewStatus;
	
	@Column(name = "REF_BOARD_CODE")
	private Long refBoardCode;
	

}
