package com.jimall.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jimall.domain.MemberVO;
import com.jimall.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime()throws Exception{
		
		System.out.println(dao.getTime());
		
	}
	
	@Test
	public void testInsertMember()throws Exception{
		
		String checkId = dao.checkId("kji831");
		
		System.out.println(checkId);
		
	}	

}


