package com.jimall.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.util.Criteria;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession session;
	public final static String NS = "com.jimall.mappers.ProductMapper";
	
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

	// 카테고리 명
	@Override
	public String getCateName(String cate_thcode) throws Exception {
		return session.selectOne(NS +".getCateName", cate_thcode);
	}

	// 카텔고리에 해당하는 상품 리스트
	@Override
	public List<ProductVO> productListCate(Map map) throws Exception {
		return session.selectList(NS +".productListCate", map);
	}

	// 상품 개수
	@Override
	public int productCount(String cate_thcode) throws Exception {
		return session.selectOne(NS +".productCount", cate_thcode);
	}

	// 검색조건에 해당하는 상품리스트
	@Override
	public List<ProductVO> productListCri(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(NS +".productListCri", cri);
	}

	// 상품 검색에 따른 개수
	@Override
	public int productCriCount(String keyword) throws Exception {
		return session.selectOne(NS +".productCriCount", keyword);
	}

	// 상품 상세정보
	@Override
	public ProductVO readProduct(int prod_num) throws Exception {
		return session.selectOne(NS +".readProduct", prod_num);
	}
}
