package com.jimall.service;

import java.util.List;
import java.util.Map;

import com.jimall.domain.CartProductVO;
import com.jimall.domain.CartVO;

public interface CartService {

	// 장바구니 가져오기
	public List<CartProductVO> getCart(String spmem_id) throws Exception;
	 
	// 장바구니 추가
	public void addCart(CartVO vo) throws Exception;
	
	// 장바구니 삭제
	public void deleteCart(int cart_code) throws Exception;
	
	// 장바구니 수량 변경
	public void updtateCart(Map map) throws Exception;
}
