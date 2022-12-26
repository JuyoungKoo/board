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
@Table(name ="board_tbl")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_CODE")
	private Long boardCode;
	
	@Column(name = "BOARD_TITLE")
	private String boardTitle;
	
	@Column(name = "BOARD_CONTENT")
	private String boardContent;
	
	@Column(name = "BOARD_REGISTDATE")
	private java.sql.Date boardRegistDate;
	
	@Column(name = "BOARD_STATUS")
	private String boardStatus;
	
	@Column(name = "BOARD_WRITER")
	private String boardWriter;
	
	@ManyToOne
	@JoinColumn(name = "TYPE_CODE")
	private BoardType boardType;
	
	

}
