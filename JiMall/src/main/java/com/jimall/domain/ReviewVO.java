package com.jimall.domain;

import java.util.Date;

public class ReviewVO {

	/*
	 REV_NUM         NUMBER                      PRIMARY KEY,
        SPMEM_ID        VARCHAR2(50)                NOT NULL,
        PROD_NUM        NUMBER                      NOT NULL,
        REV_CONTENT     VARCHAR2(200)               NOT NULL,
        REV_SCORE       NUMBER                      NOT NULL,
        REV_REGDATE     DATE DEFAULT SYSDATE        NOT NULL,
	
	*/
	
	private int rev_num;
	private String spmem_id;
	private int prod_num;
	private String rev_content;
	private int rev_score;
	private Date rev_regdate;
	
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}
	public String getSpmem_id() {
		return spmem_id;
	}
	public void setSpmem_id(String spmem_id) {
		this.spmem_id = spmem_id;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	
	public int getRev_score() {
		return rev_score;
	}
	public void setRev_score(int rev_score) {
		this.rev_score = rev_score;
	}
	public Date getRev_regdate() {
		return rev_regdate;
	}
	public void setRev_regdate(Date rev_regdate) {
		this.rev_regdate = rev_regdate;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [rev_num=" + rev_num + ", spmem_id=" + spmem_id + ", prod_num=" + prod_num + ", rev_content="
				+ rev_content + ", rew_score=" + rev_score + ", rev_regdate=" + rev_regdate + "]";
	}
	
	
}
