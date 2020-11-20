package com.jimall.domain;

import java.util.Date;

public class OrderVO {

	/*CREATE TABLE ORDER_TBL(
        ORD_NUM             NUMBER                  PRIMARY KEY,
        SPMEM_ID            VARCHAR2(50)            NOT NULL,
        ORD_NAME            VARCHAR2(30)            NOT NULL,
        ORD_POSTCORD        CHAR(5)                 NOT NULL,
        ORD_BASADD          VARCHAR2(50)            NOT NULL,
        ORD_DEADD           VARCHAR2(50)            NOT NULL,
        ORD_TEL             VARCHAR2(20)            NOT NULL,
        ORD_PRICE           NUMBER                  NOT NULL,
        ORD_ORDDATE         DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(SPMEM_ID) REFERENCES SHOPINGMEMBER_TBL(SPMEM_ID)
);*/
	
	private int ord_num;
	private String spmem_id;
	private String ord_name;
	private String ord_postcord;
	private String ord_basadd;
	private String ord_deadd;
	private String ord_tel;
	private int ord_price;
	private Date ord_orddate;
	
	public int getOrd_num() {
		return ord_num;
	}
	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}
	public String getSpmem_id() {
		return spmem_id;
	}
	public void setSpmem_id(String spmem_id) {
		this.spmem_id = spmem_id;
	}
	public String getOrd_name() {
		return ord_name;
	}
	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}
	public String getOrd_postcord() {
		return ord_postcord;
	}
	public void setOrd_postcord(String ord_postcord) {
		this.ord_postcord = ord_postcord;
	}
	public String getOrd_basadd() {
		return ord_basadd;
	}
	public void setOrd_basadd(String ord_basadd) {
		this.ord_basadd = ord_basadd;
	}
	public String getOrd_deadd() {
		return ord_deadd;
	}
	public void setOrd_deadd(String ord_deadd) {
		this.ord_deadd = ord_deadd;
	}
	public String getOrd_tel() {
		return ord_tel;
	}
	public void setOrd_tel(String ord_tel) {
		this.ord_tel = ord_tel;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public Date getOrd_orddate() {
		return ord_orddate;
	}
	public void setOrd_orddate(Date ord_orddate) {
		this.ord_orddate = ord_orddate;
	}
	
	@Override
	public String toString() {
		return "OrderVO [ord_num=" + ord_num + ", spmem_id=" + spmem_id + ", ord_name=" + ord_name + ", ord_postcord="
				+ ord_postcord + ", ord_basadd=" + ord_basadd + ", ord_deadd=" + ord_deadd + ", ord_tel=" + ord_tel
				+ ", ord_price=" + ord_price + ", ord_orddate=" + ord_orddate + "]";
	}
	
	
}
