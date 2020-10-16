package com.jimall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimall.domain.AdminVO;
import com.jimall.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	// 로그인
	@Override
	public AdminVO login(AdminVO vo) throws Exception {
		
		vo = dao.login(vo);
		
		return vo;
	}

}
