package com.jimall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.util.SearchCriteria;

@Repository
public class AdminProductDAOImpl implements AdminProductDAO {

	@Autowired
	SqlSession session;
	
	public final static String NS="com.jimall.mappers.AdminProductMapper";
	
	// 메인 카테고리
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		return session.selectList(NS+ ".mainCateList");
	}
	
	// 1차 카테고리에 따른 2차 카테고리
	@Override
	public List<CategoryVO> subCateList(String cate_thcode) throws Exception {
		return session.selectList(NS+ ".subCateList", cate_thcode);
	}
	
	// 상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		session.insert(NS +".insertProduct", vo);
	}

	// 상품 리스트
	@Override
	public List<ProductVO> searchList(SearchCriteria cri) throws Exception {

		return session.selectList(NS+".searchList", cri);
	}

	// 검색 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".searchListCount", cri);
	}

	// 상품 정보
	@Override
	public ProductVO readProduct(int prod_num) throws Exception {
		return session.selectOne(NS +".readProduct", prod_num);
	}

	

	

}
