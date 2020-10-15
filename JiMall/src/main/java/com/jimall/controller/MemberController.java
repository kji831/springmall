package com.jimall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jimall.controller.MemberController;
import com.jimall.domain.MemberVO;
import com.jimall.dto.MemberDTO;
import com.jimall.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// MemberService를 참조하기 위한 구문
	@Autowired  // 이노테이션 제발 까먹지 말기
	private MemberService service;
	
	// 비밀번호 암호화를 위한 구문 : spring-security에서 빈객체로 생성
	@Inject
	private BCryptPasswordEncoder passSecu;
	
	// 회원가입 
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String memberJoin() {
		
		return "/member/join";
	}
	
	// 회원가입 전송
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memberJoinOk(MemberVO vo, RedirectAttributes redirect) throws Exception {
		
		logger.info("입력데이터 : " + vo.toString());
		
		// 비밀번호 암호화 처리(join구문 적기 전에 적어줘야 됨)
		passSecu.encode(vo.getSpmem_pw());
		
		service.join(vo);
		
		return "redirect:/";
	}

	// 아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "checkId", method = RequestMethod.POST)
	public ResponseEntity<String> checkId(@RequestParam("spmem_id") String spmem_id) throws Exception{

		logger.info("=====checkId....=====");
		ResponseEntity<String> entity = null;

		try {
			String id = service.checkId(spmem_id);

			if(id != spmem_id){
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

	// 이메일 인증코드
	@ResponseBody
	@RequestMapping(value = "checkAuthcode", method = RequestMethod.POST)
	public ResponseEntity<String> checkAuthcode(@RequestParam("code") String code, HttpSession session){
		
		ResponseEntity<String> entity = null;

		try{

			if(code.equals(session.getAttribute("authcode"))){
				// 인증번호 일치
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}else{
				// 인증번호 불일치
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}

		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 로그인(get)
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginGET() {
		
		return "/member/login";
	}
	
	
	// 로그인(post)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String memberLoginPOST(MemberDTO dto, RedirectAttributes rttr, HttpSession session, Model model) throws Exception {
		
		logger.info("login test....");
		
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) { // 로그인 성공
			//logger.info("=====로그인 성공=====");
			
			session.setAttribute("user", memDTO);
			
			// rttr.addFlashAttribute("msg", "LOGIN_SUCCESS");
			return "redirect:/";
		}else {
			//logger.info("=====로그인 실패=====");
			
			// rttr.addFlashAttribute("msg", "LOGIN_FAIL");
			
			return "redirect:/member/login";
		}
		
		
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		logger.info("=====logout...=====");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 비밀번호 재확인 - 회원정보 수정으로 들어 갈 때
	@RequestMapping(value = "checkPw", method = RequestMethod.GET)
	public void checkPw(@ModelAttribute("url") String url) {
		
		// 회원수정을 눌렀을 때 나오는 페이지 
		
	}
	
	// 비밀번호 재확인 보내기
	@RequestMapping(value = "checkPw", method = RequestMethod.POST)
	public String checkPwPOST(@RequestParam("url") String url, @RequestParam("spmem_pw") String pw,
							   HttpSession session, Model model) throws Exception {
		
		// getAttribue("user") -> 로그인 한 정보??
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		//if(passSecu.matches(pw, dto.getSpmem_pw())) {
			
			if(url.equals("modify")) {
				model.addAttribute("vo", service.readMemInfo(dto.getSpmem_id()));
				return "/member/modify";
				
			}else if(url.equals("changePw")) {
				return "/member/changePw";
				
			}else if(url.equals("delete")) {
				return "/member/delete";
			}
		//}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", "CHECK_PW_FAIL");
		
		return "/member/choice";
	}
	
	// 비밀번호 ajax
	@ResponseBody
	@RequestMapping("checkPwAjax")
	public ResponseEntity<String> checkPwAjax(@RequestParam("mem_pw") String spmem_pw, HttpSession session){
		
		ResponseEntity<String> entity = null;
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		if(passSecu.matches(spmem_pw, dto.getSpmem_pw())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		 
		return entity;
	}
	
	// 회원수정
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(MemberVO vo, HttpSession session) throws Exception{
		
		MemberDTO dto = new MemberDTO();
		dto.setSpmem_id(vo.getSpmem_id());
		dto.setSpmem_pw(vo.getSpmem_pw());
		
		// 비밀번호 암호화
		vo.setSpmem_pw(passSecu.encode(vo.getSpmem_pw()));
		service.modifyMemInfo(vo); // 회원수정
		
		// 로그인시 세션에 저장했던 정보가 다른곳에서 사용 될 경우 변경 된 정보를 세션에 새로 반영하는 코드를 적어야 함
		session.setAttribute("user", service.login(dto));
		
		return "redirect:/";
	}
	
	// 비밀번호 변경
	@RequestMapping(value = "changePw", method = RequestMethod.POST)
	public String changePw(MemberDTO dto, HttpSession session)throws Exception {
		
		// 받은 비밀번호를 암호화해서 변경
		dto.setSpmem_pw(passSecu.encode(dto.getSpmem_pw()));
		service.changePw(dto);
		
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		memDTO.setSpmem_pw(dto.getSpmem_pw());
		session.setAttribute("user", memDTO);
		
		return "redirect:/";
	}
	
	// 회원 탈퇴
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteMem(String spmem_id, HttpSession session) throws Exception{
		
		service.deleteMem(spmem_id);
		
		// 세션 소멸 작업
		session.invalidate();
		
		return "redirect:/";
	}
	
}
