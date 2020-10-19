package com.jimall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	
	/*
	  PROD_NUM             NUMBER                  PRIMARY KEY,
        CATE_THCODE          VARCHAR2(20)            NOT NULL,
        PROD_NAME            VARCHAR2(50)            NOT NULL,
        PROD_PRICE           NUMBER                  NOT NULL,
        PROD_DISCOUNT        NUMBER                  NOT NULL,
        PROD_DEVELOP         VARCHAR2(30)            NOT NULL,
        PROD_DETAILINT       CLOB                    NOT NULL,
        PROD_IMG             VARCHAR(50)             NOT NULL,
        PROD_COMINVEN        NUMBER                  NOT NULL,
        PROD_BUYPOS          CHAR(1)                 NOT NULL,
        PROD_REGISDATE       DATE DEFAULT SYSDATE    NOT NULL,
        PROD_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(CATE_THCODE) REFERENCES CATEGORY_TBL(CATE_THCODE)
	 */
	
	private int prod_num;
	private String cate_thcode;
	private String prod_name;
	private int prod_price;
	private int prod_discount;
	private String prod_develop;
	private String prod_detailint;
	private String prod_img;
	private int prod_cominven;
	private String prod_buypos;
	private Date prod_regisdate;
	private Date prod_updateDate;
	
	

	// 업로드 파일
	private MultipartFile file1;
	
	
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public String getCate_thcode() {
		return cate_thcode;
	}
	public void setCate_thcode(String cate_thcode) {
		this.cate_thcode = cate_thcode;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getProd_discount() {
		return prod_discount;
	}
	public void setProd_discount(int prod_discount) {
		this.prod_discount = prod_discount;
	}
	public String getProd_develop() {
		return prod_develop;
	}
	public void setProd_develop(String prod_develop) {
		this.prod_develop = prod_develop;
	}
	public String getProd_detailint() {
		return prod_detailint;
	}
	public void setProd_detailint(String prod_detailint) {
		this.prod_detailint = prod_detailint;
	}
	public String getProd_img() {
		return prod_img;
	}
	public void setProd_img(String prod_img) {
		this.prod_img = prod_img;
	}
	public int getProd_cominven() {
		return prod_cominven;
	}
	public void setProd_cominven(int prod_cominven) {
		this.prod_cominven = prod_cominven;
	}
	public String getProd_buypos() {
		return prod_buypos;
	}
	public void setProd_buypos(String prod_buypos) {
		this.prod_buypos = prod_buypos;
	}
	public Date getProd_regisdate() {
		return prod_regisdate;
	}
	public void setProd_regisdate(Date prod_regisdate) {
		this.prod_regisdate = prod_regisdate;
	}
	public Date getProd_updateDate() {
		return prod_updateDate;
	}
	public void setProd_updateDate(Date prod_updateDate) {
		this.prod_updateDate = prod_updateDate;
	}
	
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	
	@Override
	public String toString() {
		return "ProductVO [prod_num=" + prod_num + ", cate_thcode=" + cate_thcode + ", prod_name=" + prod_name
				+ ", prod_price=" + prod_price + ", prod_discount=" + prod_discount + ", prod_develop=" + prod_develop
				+ ", prod_detailint=" + prod_detailint + ", prod_img=" + prod_img + ", prod_cominven=" + prod_cominven
				+ ", prod_buypos=" + prod_buypos + ", prod_regisdate=" + prod_regisdate + ", prod_updateDate="
				+ prod_updateDate + ", file1=" + file1 + "]";
	}
	
	
	
	
	
}
