package com.jimall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jimall.domain.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	private final static String NS = "com.jimall.mappers.AdminMapper";
	
	@Autowired
	private SqlSession session;
	
	// 로그인
	@Override
	public AdminVO login(AdminVO vo) throws Exception {

		return session.selectOne(NS+ ".login", vo);
	}

}
