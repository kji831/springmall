package com.jimall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.persistence.AdminProductDAO;

@Service
public class AdminProductServiceImpl implements AdminProductService {

	@Autowired
	AdminProductDAO dao;
	
	// 1차 카테고리
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		return dao.mainCateList();
	}

	// 1차 카테고리에 따른 2차카테고리
	@Override
	public List<CategoryVO> subCateList(String cate_thcode) throws Exception {
		return dao.subCateList(cate_thcode);
	}
	
	// 상품등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {

		dao.insertProduct(vo);
		
	}

	

}
