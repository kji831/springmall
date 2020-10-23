package com.jimall.persistence;

import java.util.List;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.util.SearchCriteria;

public interface AdminProductDAO {

	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 1차카테고리에 따른 2차 카테고리
	public List<CategoryVO> subCateList(String cate_thcode) throws Exception;
	
	// 상품 등록
	public void insertProduct(ProductVO vo) throws Exception;
	
	// 상품 리스트
	public List<ProductVO> searchList(SearchCriteria cri) throws Exception;
	
	// 검색 조건에 해당하는 상품 개수
	public int searchListCount(SearchCriteria cri) throws Exception;
	
	// 상품 정보 읽기
	public ProductVO readProduct(int prod_num) throws Exception;
}
