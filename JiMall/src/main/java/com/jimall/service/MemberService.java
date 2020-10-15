package com.jimall.service;

import com.jimall.domain.MemberVO;
import com.jimall.dto.MemberDTO;

public interface MemberService {

	// 회원가입
	public void join(MemberVO vo) throws Exception;

	// 아이디 중복체크
	public String checkId(String spmem_id) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;

	// 회원정보 수정
	public void modifyMemInfo(MemberVO vo) throws Exception;
	
	// 비밀번호 변경
	public void changePw(MemberDTO dto) throws Exception;
	
	// 회원 탈퇴
	public void deleteMem(String spmem_id) throws Exception;
	
	// MemberVO
	public MemberVO readMemInfo(String spmem_id) throws Exception;
}
