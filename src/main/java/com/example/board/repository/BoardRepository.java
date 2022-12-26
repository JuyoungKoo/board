package com.example.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query(value = "SELECT b " + 
					"FROM Board b " + 
					"WHERE b.boardTitle LIKE '%' || :searchValue || '%' " + 
					"AND b.boardStatus = :boardStatus")
	Page<Board> findBySearchValue(@Param("searchValue")String searchvalue, @Param("boardStatus")String boardStatus, Pageable pageable);
	
	
	Page<Board> findByBoardStatus(Pageable pageable, String boardStatus);

}
