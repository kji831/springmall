package com.jimall.domain;

public class CartVO {

	/*CART_CODE         NUMBER          PRIMARY KEY,
        PROD_NUM         NUMBER          NOT NULL,
        SPMEM_ID         VARCHAR2(20)    NOT NULL,
        CART_PUAMOUNT     NUMBER          NOT NULL,
    */
	
	private int cart_code;
	private int prod_num;
	private String spmem_id;
	private int cart_puAmount;
	
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public String getSpmem_id() {
		return spmem_id;
	}
	public void setSpmem_id(String spmem_id) {
		this.spmem_id = spmem_id;
	}
	public int getCart_puAmount() {
		return cart_puAmount;
	}
	public void setCart_puAmount(int cart_puAmount) {
		this.cart_puAmount = cart_puAmount;
	}
	
	@Override
	public String toString() {
		return "CartVO [cart_code=" + cart_code + ", prod_num=" + prod_num + ", spmem_id=" + spmem_id
				+ ", cart_puamount=" + cart_puAmount + "]";
	}
	
	
}
