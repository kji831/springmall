package com.jimall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jimall.domain.ReviewVO;
import com.jimall.persistence.ReviewDAO;
import com.jimall.util.Criteria;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewDAO dao;
	
	// 상품후기 총 개수
	@Override
	public int countReview(int prod_num) throws Exception {
		return dao.countReview(prod_num);
	}

	// 상품후기 쓰기
	@Override
	public void writeReview(ReviewVO vo, String spmem_id) throws Exception {
		vo.setSpmem_id(spmem_id);
		dao.writeReview(vo);
	}

	// 상품후기 리스트
	@Override
	public List<ReviewVO> listReview(int prod_num, Criteria cri) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prod_num", prod_num);
		map.put("cri", cri);
		
		return dao.listReview(map);
	}

	// 상품후기 삭제
	@Override
	public void deleteREview(int rev_num) throws Exception {
		dao.deleteREview(rev_num);
	}

	// 상품후기 수정
	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		dao.modifyReview(vo);
	}
	
	

}
