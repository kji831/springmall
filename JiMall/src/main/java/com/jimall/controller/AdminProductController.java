package com.jimall.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jimall.domain.CategoryVO;
import com.jimall.domain.ProductVO;
import com.jimall.service.AdminProductService;
import com.jimall.util.FileUtils;
import com.jimall.util.PageMaker;
import com.jimall.util.SearchCriteria;

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
	
	// 이미지 파일 삭제
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

		
		logger.info(vo.toString());
		
		
		vo.setProd_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		
		return "redirect:/admin/product/list";
	}
	
	// 상품리스트
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void productList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());
		
		model.addAttribute("productList", service.searchList(cri));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri); // 페이징 정보  검색정보
		
		int count = service.searchListCount(cri);
		
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
		
	}
	
	// 파일 업로드 관련
	@RequestMapping(value = "imgUpload", method = RequestMethod.POST)
	public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 클라이언트에 보내기 위한 설정
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			// 폴더 경로 설정
			String uploadPath = req.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			
			// 출력 스트림 생성
			out = new FileOutputStream(new File(uploadPath));
			
			// 파일 쓰기
			out.write(bytes);
			
			// 클라이언트로 보내기 위한 정보설정 2
			printWriter = res.getWriter();
			String fileUrl = "/upload/" + fileName;
			
			// ckeditor에서 제공하는 형식
			printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush(); // 전송 return과 같은 역할 : 클라이언트로 보냄
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {out.close(); }catch(Exception e) {e.printStackTrace();}
			}
			if(printWriter != null) {
				try {printWriter.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		
	}
	
	// 상품 상세정보 페이지
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void productRead(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("prod_num") int prod_num, Model model) throws Exception {
		
		ProductVO vo = service.readProduct(prod_num);
		
		vo.setProd_img(vo.getProd_img().substring(vo.getProd_img().lastIndexOf("_")+1));
		
		model.addAttribute("vo", vo);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
	}
	
	// 상품 수정 
	@RequestMapping(value = "edit", method=RequestMethod.GET)
	public void productEditGET(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("prod_num") int prod_num, Model model) throws Exception{
		
		// 선택한 상품의 날짜 변환
		ProductVO vo = service.readProduct(prod_num);
		
		List<CategoryVO> subCateList = service.subCateList(vo.getCate_pracode());
		String originFile = vo.getProd_img().substring(vo.getProd_img().lastIndexOf("_") + 1);
		
		model.addAttribute("vo", vo);
		model.addAttribute("originFile", originFile);
		model.addAttribute("cateList", service.mainCateList());
		model.addAttribute("subCateList", service.subCateList(vo.getCate_pracode()));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
	}
	
	// 상품수정
	@RequestMapping(value = "edit", method=RequestMethod.POST)
	public String productEditPOST(ProductVO vo, SearchCriteria cri) throws Exception {
		
		// 파일 사이즈로 새로운 파일 등록 여부 확인하기
		// 파일을 새로 등록하지 않으면 비어있는 쓰레기 파일이 넘어옴
		if(vo.getFile1().getSize() > 0) {
			
			vo.setProd_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		}
		
		service.editProduct(vo);
		
		return "redirect:/admin/product/list";
	}
	
	// 선택 상품 수정
	@ResponseBody
	@RequestMapping(value = "editChecked", method = RequestMethod.POST)
	public ResponseEntity<String> editChecked(@RequestParam("checkArr[]") List<Integer> checkArr, @RequestParam("cominvenArr[]") List<Integer> cominvenArr,
											  @RequestParam("buyposArr[]") List<String> buyposArr) {
		
		ResponseEntity<String> entity = null;
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i<checkArr.size(); i++) {
				map.put("prod_num", checkArr.get(i));
				map.put("prod_cominven", cominvenArr.get(i));
				map.put("prod_buypos", buyposArr.get(i));
				
				service.editChecked(map);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		entity = new ResponseEntity<String>(HttpStatus.OK);
		return entity;
	}
	
	// 상품 삭제
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String productDelete(SearchCriteria cri, @RequestParam("prod_num") int prod_num,
			                    @RequestParam("prod_img") String prod_img, HttpSession session) throws Exception {
		
		deleteFile(prod_img);
		
		service.deleteProduct(prod_num);
		
		return "redirect:/admin/product/list";
	}
	
	
}
