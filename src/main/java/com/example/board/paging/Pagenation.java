package com.example.board.paging;

import org.springframework.data.domain.Page;

public class Pagenation {
	

	public static PagingButtonInfo getPagingButtonInfo(Page page) {
		
		//index기반으로 0 부터 시작 +1 
		int currentPage = page.getNumber() + 1;	
		//버튼갯수
		int defaultButtonCount = 10;
		int startPage;
		int endPage;
		
		startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
		endPage = startPage + defaultButtonCount - 1;
		
		if(page.getTotalPages() < endPage)
			endPage = page.getTotalPages();
		
		if(page.getTotalPages() == 0 && endPage == 0)
			endPage = startPage;
		
		return new PagingButtonInfo(currentPage, startPage, endPage);
	}


}
