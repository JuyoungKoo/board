package com.example.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
