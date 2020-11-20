package com.jimall.persistence;

import java.util.List;
import java.util.Map;

import com.jimall.domain.ReviewVO;

public interface ReviewDAO {

	// 상품후기 총 개수
	public int countReview(int prod_num) throws Exception;
	
	// 상품후기 쓰기
	public void writeReview(ReviewVO vo) throws Exception;
	
	// 상품후기 리스트
	public List<ReviewVO> listReview(Map<String, Object> map) throws Exception;
	
	// 상품후기 삭제
	public void deleteREview (int rev_num) throws Exception;
	
	// 상품후기 수정
	public void modifyReview(ReviewVO vo) throws Exception;
}
