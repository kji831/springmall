package com.jimall.persistence;

import java.util.List;
import java.util.Map;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.util.Criteria;

public interface ProductDAO {

	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 1차카테고리에 따른 2차 카테고리
	public List<CategoryVO> subCateList(String cate_thcode) throws Exception;
	
	// 카테고리 코드에 해당하는 카테고리 명
	public String getCateName(String cate_thcode) throws Exception;
	
	// 카테고리에 해당하는 상품 리스트 
	public List<ProductVO> productListCate(Map map) throws Exception;
	 
	// 상품 개수
	public int productCount(String cate_thcode) throws Exception;
	
	// 조건에 해당하는 상품리스트
	public List<ProductVO> productListCri(Criteria cri) throws Exception;
	
	// 해당 검색조건에 해당하는 상품 개수
	public int productCriCount(String keyword) throws Exception;
	
	// 상품 상세정보 읽기
	public ProductVO readProduct(int prod_num) throws Exception;
}
