package com.jimall.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimall.dto.EmailDTO;
import com.jimall.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	
	private EmailService emailService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	public ResponseEntity<String> send(@ModelAttribute EmailDTO dto, Model model, HttpSession session){
		
		logger.info("=====email send...=====");
		logger.info("=====EmailDTO :" + dto.toString());
		
		ResponseEntity<String> entity = null;
		
		// 6자리 인증코드 생성
		String authcode = "";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random()*10));
		}
		
		session.setAttribute("authcode", authcode);
		
		logger.info("=====authcode: " + authcode);
		try {
			emailService.sendMail(dto, authcode);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
}	
