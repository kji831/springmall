package com.jimall.service;

import com.jimall.domain.AdminVO;

public interface AdminService {

	//로그인
	public AdminVO login(AdminVO vo) throws Exception;
}
