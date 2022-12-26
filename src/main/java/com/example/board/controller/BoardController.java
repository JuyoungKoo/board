package com.example.board.controller;

import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDto;
import com.example.board.dto.ReviewDto;
import com.example.board.paging.Pagenation;
import com.example.board.paging.PagingButtonInfo;
import com.example.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	
	private final MessageSourceAccessor messageSourceAccessor;
	private final BoardService boardService;
	
	/*bean 으로 등록 된 messageSourceAccessor 의존성주입*/
	//@Autowired 생략가능
	public BoardController(MessageSourceAccessor messageSourceAccessor,BoardService boardService) {
		
		this.messageSourceAccessor = messageSourceAccessor;
		this.boardService = boardService;
		
	}
	
	/* 게시판 리스트 */
	@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue="1") int page,
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[BoardController]====================");
		
		Page<BoardDto> boardList = boardService.findboardList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		
		log.info("[BoardController] boardList : {} ", boardList);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			
			model.addAttribute("searchValue" , searchValue);
			
		}
		
		log.info("[BoardController] searchValue : {} ", searchValue);
		
		return "board/boardList";
		
	}
	
	/* 게시글 등록 화면연결 */
	@GetMapping("/boardRegist")
	public String boardRegistPage() {
		return "board/boardRegist";
	}
	
	/* 게시글 등록 */
	@PostMapping("/boardRegist")
	public String boardRegist(Model model, BoardDto board, RedirectAttributes rttr) {
		
		boardService.boardRegist(board);
		
		model.addAttribute("board",board);
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("board.regist"));
		
		log.info("[BoardController] boardRegistend ==========================");
			
		return "redirect:/boardList";
	}
	
	/* 게시글 상세조회*/
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, Long boardCode) {
		
		log.info("[BoardController] boardDetail start====================");
		log.info("[BoardController] boardCode {} : " , boardCode);
		
		BoardDto board = boardService.selectBoardDetail(boardCode);
		
		log.info("[BoardController] boardCode  : {}  " , board);
		
		model.addAttribute("board", board);
		
		log.info("[BoardController] boardDetail end====================");
		
		
		return "board/boardDetail";
	}
	
	/* 게시글 수정 페이지 이동*/
	@GetMapping("/boardModify")
	public String boardModifyPage(Model model, Long boardCode) {
		
		
		BoardDto board = boardService.selectBoardDetail(boardCode);
		
		
		model.addAttribute("board", board);
		
		
		return "board/boardModify";
	}
	
	/* 게시글 수정 */
	@PostMapping("/boardModify")
	public String boardModify(Model model, BoardDto board, RedirectAttributes rttr) {
		
		log.info("[BoardController] boardModify start====================");
		log.info("[BoardController] boardCode  : {}  " , board);
		
		boardService.boardModify(board);
		
		log.info("[BoardController] boardCode  : {}  " , board);
		
		model.addAttribute(board);
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("board.modify"));
		
		log.info("[BoardController] boardModify end====================");
		
		
		return "redirect:/boardDetail?boardCode=" + board.getBoardCode();
		
	}
	
	/* 게시글 삭제 */
	@PostMapping("/boardDelete")
	public String boardDelete(Model model, BoardDto board, RedirectAttributes rttr) {
		
		log.info("[BoardController] boardDelete start====================");
		log.info("[BoardController] board  : {}  " , board);
		
		boardService.boardDelete(board);
		
		log.info("[BoardController] board  : {}  " , board);
		
		model.addAttribute(board);
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("board.delete"));
		
		log.info("[BoardController] boardDelete end====================");
		
		
		return "redirect:/boardList";
		
	}
	
	
	/* 리뷰등록 */
	@PostMapping("/registReview")
	public ResponseEntity<String> registReview(@RequestBody ReviewDto registReview) {
		
		log.info("[BoardController] registReview Start ====================== ");
		
		log.info("[BoardController] registReview : {}", registReview);
		
		
		// 댓글 입력 후 업데이트 된 해당 게시글의 댓글 목록 조회해서 반환
		boardService.registReview(registReview);
		
		log.info("[BoardController] registReview End ====================== ");
		
		
		return ResponseEntity.ok("댓글 등록 완료");
		
	}	
	
	/* 리뷰조회 */
	@GetMapping("/reviewList")
	public ResponseEntity<List<ReviewDto>> reviewList(ReviewDto loadReview) {
		
		
		List<ReviewDto> reviewList = boardService.loadReview(loadReview);
		
		
		return ResponseEntity.ok(reviewList);
		
	}
	
	/* 리뷰삭제 */
	@PostMapping("/deleteReview")
	public ResponseEntity<String> deleteReview(@RequestBody ReviewDto deleteReview) {
		
		log.info("[BoardController] deleteReview Start ====================== ");
		
		log.info("[BoardController] deleteReview : {}", deleteReview);
		
		
		boardService.deleteReview(deleteReview);
		
		log.info("[BoardController] deleteReview : {}", deleteReview);
		
		
		log.info("[BoardController] deleteReview End ====================== ");
		
		
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	

	
	
	

}
