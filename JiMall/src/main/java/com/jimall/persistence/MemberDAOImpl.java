package com.jimall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jimall.domain.MemberVO;
import com.jimall.dto.MemberDTO;

/*
	SqlSessin이나 String NS는 직접 쳐서 눈에 익숙해지게 작업해야 한다
	"com.jimall.mappers.MemberMapper" 이거는 경로가 틀리면 안 되기 때문에 오타없게 복사해서 작업
*/

@Repository
public class MemberDAOImpl implements MemberDAO {

	private final static String NS = "com.jimall.mappers.MemberMapper";
	
	@Autowired
	private SqlSession session;
	
	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {

		session.insert(NS+ ".join", vo);
		
	}
	
	// 아이디 중복체크
	@Override
	public String checkId(String spmem_id) throws Exception {
		return session.selectOne(NS+ ".checkId", spmem_id);
	}
	
	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception{
		
		return session.selectOne(NS+ ".login", dto);
	}

	// 회원정보 수정
	@Override
	public void modifyMemInfo(MemberVO vo) throws Exception {

		session.update(NS+ ".modifyMemInfo", vo);
	}

	// 비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {
		session.update(NS + ".changePw", dto);
	}

	// 회원 탈퇴
	@Override
	public void deleteMem(String spmem_id) throws Exception {
		session.delete(NS+".deleteMem", spmem_id);
	}

	// MemberVO
	@Override
	public MemberVO readMemInfo(String spmem_id) throws Exception {
		return session.selectOne(NS+".readMemInfo", spmem_id);
	}

}
