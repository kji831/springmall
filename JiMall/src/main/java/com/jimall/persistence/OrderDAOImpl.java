package com.jimall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jimall.domain.OrderDetailVO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SqlSession session;
	public final static String NS = "com.jimall.mappers.OrderMapper";
	
	@Override
	public int readOrderNum() throws Exception {
		return session.selectOne(NS+ ".readOrderNum");
	}

	@Override
	public void addOrderDetail(OrderDetailVO vo) throws Exception {
		session.insert(NS+".addOrderDetail", vo);
	}

}
