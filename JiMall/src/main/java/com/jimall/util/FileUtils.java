package com.jimall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

//파일 업로드 위한 util 클래스
public class FileUtils {
	
	/*
	   파일 업로드
	  
	  @Params
	  String uploadPath : 기본 파일 업로드 경로
	  String originName : 원본 파일명
	  byte[] fileData : 파일 데이터
	  
	  @return 
	  String uploadedFileName : 날짜 경로 + 파일이름 
	  
	  썸네일로 넘어 오는지 확인하고 안 돼있으면 _s붙여서 썸네일 이미지로 리턴 되게 작업해주기
	 */
	
	public static String uploadFile(String uploadPath, String originName, byte[] fileData) throws Exception {
		
		System.out.println("======uploadFile() =======");
		
		// 파일 이름설정
		UUID uuid = UUID.randomUUID(); // 파일명 중복 방지
		String saveName = uuid.toString() + "_" + originName;
		
		// 파일 경로 설정
		String savePath = calcPath(uploadPath);  
		
		// 설정한 정보로 빈 파일 성성
		File target = new File(uploadPath + savePath, saveName);
		
		// 만든 파일에 데이터 씀
		FileCopyUtils.copy(fileData, target);
		
		// 확장자 명만 가져오기
		String formatName = originName.substring(originName.lastIndexOf(".") +1);
		String uploadFileName = null;
		
		// 이미지 파일인지 확인하고 맞다면 썸네일 생성
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savePath, saveName);
		}else {
			uploadFileName = makeIcon(uploadPath, savePath, saveName);
		}
		
		return uploadFileName;
		
	}

	/* 날짜 폴더 경로 설정
	
		String uploadPath : 기본 파일 업로드 경로
	
	*/
	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();
		
		// 년
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		// 년 + 월
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		
		// 년 + 월 + 일
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	/* 폴더 생성 메서드
	   
	   String uploadPath : 기본 파일 업로드 폴더 경로
	   String... paths : 생성 할 폴더 경로들
	
	*/
	
	private static void makeDir(String uploadPath, String... paths) {

		// 가장 마지막 매개변수의 폴더가 존재하는지 확인하고 존재하면 return
		if(new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		// 매개변수로 들어온 경로의 모든 폴더 생성
		for(String path: paths) {
			File dirPath = new File(uploadPath + path);
			// 해당 폴더가 없으면
			if(!dirPath.exists()) {
				dirPath.mkdir(); // 폴더생성
			}
		}
		
	}
	
	/*
	    이미지 파일 썸네일 생성 
	    
	  String uploadPath : 기본 파일 업로드 경로
	  String path : 날짜 경로
	  String fileName : UUID_originName
	   
	 */

	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		// 썸네일 높이를 80px로 하고 너비 맞춤
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 120);
		
		// 썸네일 생성 준비
		String thumbNailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbNailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		// 썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 생성한 썸네일 경로의 subString 을 반환
		System.out.println("=====makeThumbNail() thumbNail: " + thumbNailName);
		
		return thumbNailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	/*
	 	일반 파일의 아이콘 생성
	 	
	 	
	 */
	private static String makeIcon(String uploadPath, String path, String fileName) {
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	/*
	 파일 가져오기
	 웹 프로젝트 외부영역의 파일을 가져와서 ResponseEntity로 반환
	 
	 String uploadPath : 외부 폴더 업로드 경로
	 String fileName : 가져올 파일 명
	 
	 */
	
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception {
		
		InputStream in = null;
		byte[] fileData = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 파일 확장자로 파일 종류 확인
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType type = MediaUtils.getMediaType(formatName);
			
			// 파일 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			// 파일 가져옴
			in = new FileInputStream(uploadPath + fileName);
			
			// entity 생성
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		
		return entity;
	}
	
	public static void deleteFile(String uploadPath, String fileName) {
		
		// 날짜경로 + UUID_fileName
		String front = fileName.substring(0, 12); // 날짜경로
		String end = fileName.substring(14); // UUID_fileName
		String origin = front + end;
		
		new File(uploadPath + origin.replace('/', File.separatorChar)).delete();  // 원본파일 지우기
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();  // 썸네일 파일 지우기
		
	}
	
	// 썸네일 파일명 -> 원래로 
	public static String thumbToOriginName(String thumbnailName) {
		
		String front = thumbnailName.substring(0, 12);  // 날짜 경로
		String end = thumbnailName.substring(14);  // UUID_fileName
		
		return front+end;
		
	}

}
