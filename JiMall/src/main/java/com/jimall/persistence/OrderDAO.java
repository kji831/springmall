package com.jimall.persistence;

import com.jimall.domain.OrderDetailVO;

public interface OrderDAO {
	
	// 주문 시퀀스 가져오기
	public int readOrderNum() throws Exception;
	
	// 주문내역 추가
	public void addOrderDetail(OrderDetailVO vo) throws Exception;

}
