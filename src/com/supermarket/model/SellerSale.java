package com.supermarket.model;

public class SellerSale {
	private int id;
	private int seller_id;
	private String date;
	private double money;

	public SellerSale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerSale(int id, int seller_id, String date, double money) {
		super();
		this.id = id;
		this.seller_id = seller_id;
		this.date = date;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
