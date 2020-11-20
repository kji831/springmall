package com.jimall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimall.domain.CartProductVO;
import com.jimall.domain.CartVO;
import com.jimall.persistence.CartDAO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO dao;
	
	// 장바구니 리스트
	@Override
	public List<CartProductVO> getCart(String spmem_id) throws Exception {
		return dao.getCart(spmem_id);
	}

	// 장바구니 추가
	@Override
	public void addCart(CartVO vo) throws Exception {
		dao.addCart(vo);
	}

	// 장바구니 삭제
	@Override
	public void deleteCart(int cart_code) throws Exception {
		dao.deleteCart(cart_code);
	}

	// 장바구니 수량 변경
	@Override
	public void updtateCart(Map map) throws Exception {
		dao.updateCart(map);
	}

	

}
