package com.jimall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jimall.domain.ProductVO;
import com.jimall.dto.MemberDTO;
import com.jimall.service.MemberService;
import com.jimall.service.OrderService;
import com.jimall.service.ProductService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject
	private OrderService service;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public void buy(@RequestParam int ode_puamount, @RequestParam int prod_num, Model model, HttpSession session) throws Exception {
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(prod_num));
		amountList.add(ode_puamount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readMemInfo(dto.getSpmem_id()));
	}
	
	@RequestMapping(value = "buyFromCart", method = RequestMethod.GET)
	public String buyFromCartGET(@RequestParam int ode_puamount, @RequestParam int prod_num, Model model, HttpSession session) throws Exception {
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(prod_num));
		amountList.add(ode_puamount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readMemInfo(dto.getSpmem_id()));
		
		return "/order/buyFromCart";
	}
	
	@RequestMapping(value = "buyFromCart", method=RequestMethod.POST)
	public void buyFromCartPOST(@RequestParam("check") List<Integer> checkList, @RequestParam("prod_num") List<Integer> prod_numList,
								@RequestParam("cart_puamount") List<Integer> cart_puamountList, @RequestParam("cart_code") List<Integer> cart_codeList,
								Model model, HttpSession session) throws Exception {
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		for(int i=0; i<cart_codeList.size(); i++) {
			for(int j=0; j<checkList.size(); j++) {
				
				if(cart_codeList.get(i) == checkList.get(j)) {
					productList.add(productService.readProduct((int)prod_numList.get(i)));
					
					amountList.add(cart_puamountList.get(i));
					continue;
					
				} else {
					continue;
				}
 			}
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readMemInfo(dto.getSpmem_id()));
		
		
	}
}
