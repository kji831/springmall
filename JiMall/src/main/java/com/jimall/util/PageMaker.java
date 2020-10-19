package com.jimall.util;

public class PageMaker {

	private int totalCount;
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 5;
	
	// 1 2 3 4 5 : 페이지 번호에 링크기능 적용시 사용 할 파라미터
	// this.page = 1; = 현재 페이지의 번호   this.perPageNum = 5; 페이지 출력 게시물 개수
	
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData() {
		
		// 현재 페이지에서의 종료 페이지
		endPage = (int)(Math.ceil.getPage() / (double)displayPageNum) * displayPageNum);
	}
}
