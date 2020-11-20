package com.jimall.domain;

public class OrderDetailVO {

	private int ord_num;
	private int prod_num;
	private int ode_puamount;
	private int ode_price;
	
	public int getOrd_num() {
		return ord_num;
	}
	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public int getOde_puamount() {
		return ode_puamount;
	}
	public void setOde_puamount(int ode_puamount) {
		this.ode_puamount = ode_puamount;
	}
	public int getOde_price() {
		return ode_price;
	}
	public void setOde_price(int ode_price) {
		this.ode_price = ode_price;
	}
	
	@Override
	public String toString() {
		return "OrderDetailVO [ord_num=" + ord_num + ", prod_num=" + prod_num + ", ode_puamount=" + ode_puamount
				+ ", ode_price=" + ode_price + "]";
	}
	
	
}
