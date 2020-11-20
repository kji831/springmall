package com.jimall.service;

import java.util.List;

import com.jimall.domain.ReviewVO;
import com.jimall.util.Criteria;

public interface ReviewService {

	// 상품후기 총 개수
	public int countReview(int prod_num) throws Exception;
	
	// 상품후기 쓰기
	public void writeReview(ReviewVO vo, String spmem_id) throws Exception;
	
	// 상품후기 리스트
	public List<ReviewVO> listReview(int prod_num, Criteria cri) throws Exception;
	
	// 상품후기 삭제
	public void deleteREview (int rev_num) throws Exception;
		
	// 상품후기 수정
	public void modifyReview(ReviewVO vo) throws Exception;
}
