package com.jimall.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jimall.service.ProductService;

@ControllerAdvice(basePackages = {"com.jimall.controller"})
public class GlobalControllerAdvice {

	@Inject
	private ProductService service;
	
	@ModelAttribute
	public void categoryList(Model model) throws Exception {
		model.addAttribute("userCategoryList", service.mainCateList());
	}
	 
}
