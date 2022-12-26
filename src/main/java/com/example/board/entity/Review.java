package com.example.board.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
