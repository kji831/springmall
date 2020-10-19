package com.jimall.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

// 이미지 확장자 가지고 있어서 이미지파일인지 확인하기 위한 유틸
public class MediaUtils {

	// 이미지 파일의 확장자를 가지고 있는 Map
	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<String, MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPG", MediaType.IMAGE_GIF);
		mediaMap.put("JPG", MediaType.IMAGE_PNG);
	}
	
	// 이미지 파일인지 확인하는 메서드
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
	
	
}
