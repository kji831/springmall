package com.jimall.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimall.domain.MemberVO;
import com.jimall.dto.MemberDTO;
import com.jimall.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Inject
	private BCryptPasswordEncoder passSecu;
	
	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		dao.join(vo);
	}

	// 아이디 중복체크
	@Override
	public String checkId(String spmem_id) throws Exception {
		return dao.checkId(spmem_id);
	}

	// 로그인
	@Transactional
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		MemberDTO memDTO = dao.login(dto);
		
		// 회원가입 시 비밀번호 암호화 저장
		
//		if(memDTO != null) {
//			// 입력 비밀번호가 암호화 된 비밀번호와 일치하는지 확인   passSecu.matches(일반비밀번호, 암호화비밀번호)
//			if(passSecu.matches(dto.getSpmem_pw(), memDTO.getSpmem_pw())) { // true면 로그인 정보 존재
//				// 원래 이자리에 로그인 시간저장이 있었는데 굳이 필요한 거 같지 않아서 뺌(쿠키 작업 안 하니까)
//			}else {
//				memDTO = null;
//			}
//		}
		return memDTO;
	}

	// 회원정보 수정
	@Override
	public void modifyMemInfo(MemberVO vo) throws Exception {
		
		dao.modifyMemInfo(vo);
	}

	// 비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {

		dao.changePw(dto);
	}

	// 회원 탈퇴
	@Override
	public void deleteMem(String spmem_id) throws Exception {
		dao.deleteMem(spmem_id);
		
	}

	// MemberVO
	@Override
	public MemberVO readMemInfo(String spmem_id) throws Exception {
		return dao.readMemInfo(spmem_id);
	}
	
	
}
