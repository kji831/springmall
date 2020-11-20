package com.jimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimall.controller.ProductController;
import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.service.ProductService;
import com.jimall.service.ReviewService;
import com.jimall.util.Criteria;
import com.jimall.util.FileUtils;
import com.jimall.util.PageMaker;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Inject
	private ProductService service;
	
	@Inject
	private ReviewService reviewService;
	
	// 카테고리
	@ResponseBody
	@RequestMapping(value="subCateList/{cate_thcode}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCateList(@PathVariable("cate_thcode") String cate_thcode){
		
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCateList(cate_thcode), HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 카테고리에 해당하는 상품 리스트 출력
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list (@ModelAttribute("cri") Criteria cri, @ModelAttribute("cate_thcode") String cate_thcode, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate_thcode", cate_thcode);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.productListCate(map);
		model.addAttribute("productList", list);
		model.addAttribute("cate_name", service.getCateName(cate_thcode));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(cate_thcode);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	@RequestMapping(value = "listCri", method = RequestMethod.GET)
	public void listCri(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		List<ProductVO> list = service.productListCri(cri);
		model.addAttribute("productList", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCriCount(toString());
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	// 파일 출력
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void readProduct(@ModelAttribute("cri") Criteria cri, @RequestParam("prod_num") int prod_num, Model model) throws Exception {
		
		ProductVO vo = service.readProduct(prod_num);
		vo.setProd_img(FileUtils.thumbToOriginName(vo.getProd_img()));
		
		model.addAttribute("vo", vo);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
		model.addAttribute("totalReview", reviewService.countReview(vo.getProd_num()));
	}
}
