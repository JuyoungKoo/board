package com.example.board.dto;

import lombok.Data;

@Data
public class BoardDto {
	
	private Long boardCode;
	
	private String boardTitle;
	
	private String boardContent;
	
	private java.sql.Date boardRegistDate;
	
	private String boardStatus;
	
	private String boardWriter;
	
	private BoardTypeDto boardType;
	

}
