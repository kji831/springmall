package com.jimall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.persistence.ProductDAO;
import com.jimall.util.Criteria;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	
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

	// 카테고리 명
	@Override
	public String getCateName(String cate_thcode) throws Exception {
		return dao.getCateName(cate_thcode);
	}

	// 해당하는 카테고리 리스트
	@Override
	public List<ProductVO> productListCate(Map map) throws Exception {
		return dao.productListCate(map);
	}

	@Override
	public int productCount(String cate_thcode) throws Exception {
		return dao.productCount(cate_thcode);
	}

	@Override
	public List<ProductVO> productListCri(Criteria cri) throws Exception {
		return dao.productListCri(cri);
	}

	@Override
	public int productCriCount(String keyword) throws Exception {
		return dao.productCriCount(keyword);
	}

	@Override
	public ProductVO readProduct(int prod_num) throws Exception {
		return dao.readProduct(prod_num);
	}

}
