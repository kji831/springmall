package com.jimall.domain;

public class CartProductVO {

	private int cart_code;
	private int cart_puamount;
	
	private int prod_num;
	private String prod_name;
	private int prod_price;
	private int prod_discount;
	private String prod_img;
	
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	
	public int getProd_num() {
		return prod_num;
	}
	public int getCart_puamount() {
		return cart_puamount;
	}
	public void setCart_puamount(int cart_puamount) {
		this.cart_puamount = cart_puamount;
	}

	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
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
	public String getProd_img() {
		return prod_img;
	}
	public void setProd_img(String prod_img) {
		this.prod_img = prod_img;
	}
	@Override
	public String toString() {
		return "CartProductVO [cart_code=" + cart_code + ", cart_puamount=" + cart_puamount + ", prod_num=" + prod_num
				+ ", prod_name=" + prod_name + ", prod_price=" + prod_price + ", prod_discount=" + prod_discount
				+ ", prod_img=" + prod_img + "]";
	}
	
	
	
	
}
