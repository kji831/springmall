package com.jimall.service;

import java.util.List;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;

public interface AdminProductService {

	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 1차카테고리에 따른 2차 카테고리
	public List<CategoryVO> subCateList(String cate_thcode) throws Exception;
	
	// 상품 등록
	public void insertProduct(ProductVO vo) throws Exception;
}
