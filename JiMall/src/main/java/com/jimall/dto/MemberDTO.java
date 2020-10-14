package com.jimall.dto;

import java.util.Date;

public class MemberDTO {

	// 아이디 비밀번호 이름 닉네임 포인트 마지막로그인
	
	// 쿠키작업을 안 하는데 마지막 로그인 필요?? 안 필요???
	
	private String spmem_id;
	private String spmem_pw;
	private String spmem_nick;
	private String spmem_name;
	private int spmem_point;
	// private Date spmem_lastLogin;
	
	public String getSpmem_id() {
		return spmem_id;
	}
	public void setSpmem_id(String spmem_id) {
		this.spmem_id = spmem_id;
	}
	public String getSpmem_pw() {
		return spmem_pw;
	}
	public void setSpmem_pw(String spmem_pw) {
		this.spmem_pw = spmem_pw;
	}
	public String getSpmem_nick() {
		return spmem_nick;
	}
	public void setSpmem_nick(String spmem_nick) {
		this.spmem_nick = spmem_nick;
	}
	public String getSpmem_name() {
		return spmem_name;
	}
	public void setSpmem_name(String spmem_name) {
		this.spmem_name = spmem_name;
	}
	public int getSpmem_point() {
		return spmem_point;
	}
	public void setSpmem_point(int spmem_point) {
		this.spmem_point = spmem_point;
	}
	/*
	public Date getSpmem_lastLogin() {
		return spmem_lastLogin;
	}
	public void setSpmem_lastLogin(Date spmem_lastLogin) {
		this.spmem_lastLogin = spmem_lastLogin;
	}
	*/
	
	@Override
	public String toString() {
		return "MemberDTO [spmem_id=" + spmem_id + ", spmem_pw=" + spmem_pw + ", spmem_nick=" + spmem_nick
				+ ", spmem_name=" + spmem_name + ", spmem_point=" + spmem_point + "]";
	}
	
	
}
