package com.jimall.persistence;

import com.jimall.domain.AdminVO;

public interface AdminDAO {

	// 로그인
	public AdminVO login(AdminVO vo) throws Exception; 
}
