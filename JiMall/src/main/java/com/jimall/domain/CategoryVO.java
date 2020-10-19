package com.jimall.domain;

public class CategoryVO {

	// CATE_THCODE, CATE_PRACODE, CATE_NAME
	
	private String cate_thcode;
	private String cate_pracode;
	private String cate_name;
	
	public String getCate_thcode() {
		return cate_thcode;
	}
	public void setCate_thcode(String cate_thcode) {
		this.cate_thcode = cate_thcode;
	}
	public String getCate_pracode() {
		return cate_pracode;
	}
	public void setCate_pracode(String cate_pracode) {
		this.cate_pracode = cate_pracode;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [cate_thcode=" + cate_thcode + ", cate_pracode=" + cate_pracode + ", cate_name=" + cate_name
				+ "]";
	}
	
	
	
}
