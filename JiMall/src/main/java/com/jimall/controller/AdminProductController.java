package com.jimall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.service.AdminProductService;
import com.jimall.util.FileUtils;

@Controller
@RequestMapping("/admin/product/*")
public class AdminProductController {

	private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);
	
	@Autowired
	private AdminProductService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
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
	
	// 파일 출력
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	public void deleteFile(String fileName) {
		
		FileUtils.deleteFile(uploadPath, fileName);
		
	}
	
	// 상품등록 (get)
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void productInsertGET(Model model) throws Exception{
		
		model.addAttribute("cateList", service.mainCateList());
	}
	
	// 상품 등록
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String productInsert(ProductVO vo) throws Exception {

		
		vo.setProd_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		
		return "redirect:/admin/product/list";
	}
}
