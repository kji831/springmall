package com.jimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimall.domain.CartVO;
import com.jimall.dto.MemberDTO;
import com.jimall.service.CartService;

@Controller
@RequestMapping(value="/cart/*")
public class CartController {

	@Inject
	private CartService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public void listCart(Model model, HttpSession session) throws Exception {
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("cartProductList", service.getCart(dto.getSpmem_id()));
		
	}
	
	// 1개 추가
	@ResponseBody
	@RequestMapping(value = "add", method=RequestMethod.POST)
	public ResponseEntity<String> addCart(int prod_num, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		vo.setSpmem_id(dto.getSpmem_id());
		vo.setProd_num(prod_num);
		vo.setCart_puAmount(1);
		
		logger.info("===vo : " + vo.toString());
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
		
	}
	
	// 여러개 추가
	@ResponseBody
	@RequestMapping(value = "addMany", method=RequestMethod.POST)
	public ResponseEntity<String> addManyCart(int prod_num, int prod_cominven, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		vo.setSpmem_id(dto.getSpmem_id());
		vo.setProd_num(prod_num);
		vo.setCart_puAmount(prod_cominven);
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
		
	}
	
	// 장바구니 삭제
	@ResponseBody
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public ResponseEntity<String> deldteCart(int cart_code) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteCart(cart_code);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
	
	// 선택삭제
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method=RequestMethod.POST)
	public ResponseEntity<String> deldteCartChecked(@RequestParam("checkArr[]") List<Integer> checkArr) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		try {
			for(int cart_code : checkArr) {
				service.deleteCart(cart_code);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
	
	// 장바구니 수량 변경
	@ResponseBody
	@RequestMapping(value = "modify", method=RequestMethod.POST)
	public ResponseEntity<String> modifyCart(int cart_code, int cart_puamount) {
		
		
		
		ResponseEntity<String> entity = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cart_code", cart_code);
		map.put("cart_puAmount", cart_puamount);
		
		try {
			service.updtateCart(map);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	
}
