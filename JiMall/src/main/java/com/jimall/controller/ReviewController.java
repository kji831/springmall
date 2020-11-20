package com.jimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimall.domain.ReviewVO;
import com.jimall.dto.MemberDTO;
import com.jimall.service.ReviewService;
import com.jimall.util.Criteria;
import com.jimall.util.PageMaker;

@Controller
@RequestMapping(value = "/review/*")
public class ReviewController {

	@Inject
	private ReviewService service;
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	// 상품후기 쓰기
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) throws Exception {
		
		logger.info("vo:" + vo.toString());
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		
		service.writeReview(vo, dto.getSpmem_id());
	}
	
	// 상품후기 수정
	@ResponseBody
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(ReviewVO vo) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyReview(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			
		}
		return entity;
	}
	
	// 상품후기 삭제
	@ResponseBody
	@RequestMapping(value = "{rev_num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rev_num") int rev_num) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteREview(rev_num);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}
	
	// 상품후기 리스트
	@ResponseBody
	@RequestMapping(value = "{prod_num}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(@PathVariable("prod_num") Integer prod_num, @PathVariable("page") Integer page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReviewVO> list = service.listReview(prod_num, cri);
			
			map.put("list", list);
			
			int replyCount = service.countReview(prod_num);
			
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}
	
}
