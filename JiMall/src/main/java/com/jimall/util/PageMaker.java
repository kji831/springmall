package com.jimall.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		
		// 현재 페이지에서의 시작 페이지
		startPage = (endPage - displayPageNum) + 1;
		
		// 전체 종료 페이지
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPge() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Criteria getCri() {
		return cri;
	}
	
	// page(현재페이지)와 perPageNum(페이지당 게시물 개수)를 이용 한 쿼리행성 후 반환하기
	public String makeQuery(int page) {
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
				
		return uriComponents.toUriString();
	}
	
	// 페이지 정보와 검색조건을 이용한 쿼리스트링 생성 후 반환
	public String makeSearch(int page) {
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keword", ((SearchCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage +
				", endPage=" + endPage + ", prev=" + prev + ",next=" + next +
				", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
