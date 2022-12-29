package com.example.board.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.ReviewDto;
import com.example.board.entity.Board;
import com.example.board.entity.BoardType;
import com.example.board.entity.Review;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "boardCode";
	public static final String ACTIVE_STATUS = "NOMAL";

	private final BoardRepository boardRepository;
	public final ReviewRepository reviewRepository;
	private final ModelMapper modelMapper;
	
	public BoardService(BoardRepository boardRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
		
		this.boardRepository = boardRepository;
		this.reviewRepository = reviewRepository;
		this.modelMapper = modelMapper;
		
	}
	
	
	public Page<BoardDto> findboardList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page -1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Board> boardList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			boardList = boardRepository.findBySearchValue(searchValue, ACTIVE_STATUS, pageable);
		} else {
			boardList = boardRepository.findByBoardStatus(pageable, ACTIVE_STATUS);
		}
		
		return boardList.map(board -> modelMapper.map(board, BoardDto.class));
	}


	@Transactional
	public void boardRegist(BoardDto board) {
		
		boardRepository.save(modelMapper.map(board, Board.class));
		
		
	}


	public BoardDto selectBoardDetail(Long boardCode) {
		
		Board board = boardRepository.findById(boardCode).get();
				
		return modelMapper.map(board, BoardDto.class);
	}


	@Transactional
	public void boardModify(BoardDto board) {
		
		log.info("[BoardService] board  : {}  " , board);
		
		
		Board selectBoard = boardRepository.findById(board.getBoardCode()).get();
		
		log.info("[BoardService] board  : {}  " , board);	
	
		selectBoard.setBoardCode(board.getBoardCode());
		selectBoard.setBoardTitle(board.getBoardTitle());
		selectBoard.setBoardType(modelMapper.map(board.getBoardType(), BoardType.class));
		selectBoard.setBoardContent(board.getBoardContent());
		selectBoard.setBoardWriter(board.getBoardWriter());
		
	}


	@Transactional
	public void boardDelete(BoardDto board) {
		
		Board selectBoard = boardRepository.findById(board.getBoardCode()).get();
	
		selectBoard.setBoardStatus(board.getBoardStatus());
		
		
	}


	@Transactional
	public void registReview(ReviewDto registReview) {
		
		log.info("[BoardService] registReview Start ===============================");
		log.info("[BoardService] registReview : {} ", registReview);
		
		
		reviewRepository.save(modelMapper.map(registReview, Review.class));
		
		log.info("[BoardService] registReview End ===============================");
		
	}


	public List<ReviewDto> loadReview(ReviewDto loadReview) {
		
		List<Review> reviewList = reviewRepository.findByRefBoardCodeAndReviewStatus(loadReview.getRefBoardCode(), ACTIVE_STATUS);
		
		return reviewList.stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
	}


	@Transactional
	public void deleteReview(ReviewDto deleteReview) {
		
		log.info("[BoardService] deleteReview Start ===============================");
		log.info("[BoardService] deleteReview : {} ", deleteReview);
		
		Review foundReview = reviewRepository.findByReviewCode(deleteReview.getReviewCode());
		
		foundReview.setReviewStatus("DELETED");
		
		log.info("[BoardService] deleteReview End ===============================");
		
	}

}
