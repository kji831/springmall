package com.jimall.domain;

import java.util.Date;

public class MemberVO {

	/*
	  SPMEM_ID             VARCHAR2(15)            PRIMARY KEY,
        SPMEM_NAME           VARCHAR2(30)            NOT NULL,
        SPMEM_PW             VARCHAR2(60)            NOT NULL,
        SPMEM_POSTCODE       CHAR(5)                 NOT NULL,
        SPMEM_ADD            VARCHAR2(50)            NOT NULL,
        SPMEM_DEADD          VARCHAR2(50)            NOT NULL,
        SPMEM_PHONNUM        VARCHAR2(15)            NOT NULL,
        SPMEM_NICK           VARCHAR2(20)            NOT NULL UNIQUE,
        SPMEM_EMAILRE        CHAR(1)                 NOT NULL,
        SPMEM_SAPOINT        NUMBER                  NOT NULL,
        SPMEM_JOINDATE       DATE DEFAULT SYSDATE    NOT NULL,
        SPMEM_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL,
        SPMEM_LASTLOGIN      DATE DEFAULT SYSDATE    NOT NULL
        
        CTRL + F : 찾기, 바꾸기
        CTRL + Shift + Y : 대문자 -> 소문자
        CTRL + Shift + X : 소문자 -> 대문자
        
	 */
	
	private String spmem_id;
	private String spmem_name;
	private String spmem_pw;
	private String spmem_postcode;
	private String spmem_add;
	private String spmem_deAdd;
	private String spmem_phonNum;
	private String spmem_nick;
	private String spmem_email;
	private int spmem_point;
	private Date spmem_joinDate;
	private Date spmem_updateDate;
	private Date spmem_lastLogin;

	
	public String getSpmem_id() {
		return spmem_id;
	}
	public void setSpmem_id(String spmem_id) {
		this.spmem_id = spmem_id;
	}
	public String getSpmem_name() {
		return spmem_name;
	}
	public void setSpmem_name(String spmem_name) {
		this.spmem_name = spmem_name;
	}
	public String getSpmem_pw() {
		return spmem_pw;
	}
	public void setSpmem_pw(String spmem_pw) {
		this.spmem_pw = spmem_pw;
	}
	public String getSpmem_postcode() {
		return spmem_postcode;
	}
	public void setSpmem_postcode(String spmem_postcode) {
		this.spmem_postcode = spmem_postcode;
	}
	public String getSpmem_add() {
		return spmem_add;
	}
	public void setSpmem_add(String spmem_add) {
		this.spmem_add = spmem_add;
	}
	public String getSpmem_deAdd() {
		return spmem_deAdd;
	}
	public void setSpmem_deAdd(String spmem_deAdd) {
		this.spmem_deAdd = spmem_deAdd;
	}
	public String getSpmem_phonNum() {
		return spmem_phonNum;
	}
	public void setSpmem_phonNum(String spmem_phonNum) {
		this.spmem_phonNum = spmem_phonNum;
	}
	public String getSpmem_nick() {
		return spmem_nick;
	}
	public void setSpmem_nick(String spmem_nick) {
		this.spmem_nick = spmem_nick;
	}
	public String getSpmem_email() {
		return spmem_email;
	}
	public void setSpmem_email(String spmem_email) {
		this.spmem_email = spmem_email;
	}
	public int getSpmem_point() {
		return spmem_point;
	}
	public void setSpmem_point(int spmem_point) {
		this.spmem_point = spmem_point;
	}
	public Date getSpmem_joinDate() {
		return spmem_joinDate;
	}
	public void setSpmem_joinDate(Date spmem_joinDate) {
		this.spmem_joinDate = spmem_joinDate;
	}
	public Date getSpmem_updateDate() {
		return spmem_updateDate;
	}
	public void setSpmem_updateDate(Date spmem_updateDate) {
		this.spmem_updateDate = spmem_updateDate;
	}
	public Date getSpmem_lastLogin() {
		return spmem_lastLogin;
	}
	public void setSpmem_lastLogin(Date spmem_lastLogin) {
		this.spmem_lastLogin = spmem_lastLogin;
	}
	
	@Override
	public String toString() {
		return "MemberVO [spmem_id=" + spmem_id + ", spmem_name=" + spmem_name + ", spmem_pw=" + spmem_pw
				+ ", spmem_postcode=" + spmem_postcode + ", spmem_add=" + spmem_add + ", spmem_deAdd=" + spmem_deAdd
				+ ", spmem_phonNum=" + spmem_phonNum + ", spmem_nick=" + spmem_nick + ", spmem_email=" + spmem_email
				+ ", spmem_point=" + spmem_point + ", spmem_joinDate=" + spmem_joinDate + ", spmem_updateDate="
				+ spmem_updateDate + ", spmem_lastLogin=" + spmem_lastLogin + "]";
	}
	
	
	
	
}
