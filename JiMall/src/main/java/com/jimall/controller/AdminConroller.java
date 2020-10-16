package com.jimall.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jimall.domain.AdminVO;
import com.jimall.service.AdminService;

// 관리자 Controller 클래스

@Controller
@RequestMapping("/admin/*")
public class AdminConroller {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminConroller.class);
	
	@Autowired
	private AdminService service;

	// 데모소스에 56번 라인 쯔음
	// 관리자 로그인 시간 업데이트 작업(보완)
	// DAO,Service,mapper작업을 통해서 업데이트 작업 해야 됨
	// - 아마 update 하고 sysdate처리에 하나 해야 할 듯?? 확실하지는 않은데 데모 회원쪽 라스트 sysdate쪽 참고 해보자
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String adminHome() {
		return "admin/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(AdminVO vo, HttpSession session) throws Exception {
		
		vo = service.login(vo);
		
		if(vo != null) {
			session.setAttribute("admin", vo);
		}else {
			
		}
		
		return "/admin/main";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(AdminVO vo, HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "/admin/login";
	}
	
}
