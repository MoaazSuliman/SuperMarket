package com.supermarket.model;

public class Sale {
	private int product_id;
	private String name;
	private double price;
	private double quantity;
	private double total;
	private boolean c;

	public Sale() {
		super();
		this.c = false;
		// TODO Auto-generated constructor stub
	}

	public Sale(int product_id, String name, double price, double quantity, double total) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.product_id = product_id;
		this.c = false;
	}

	/**
	 * @return the c
	 */
	public boolean getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(boolean c) {
		this.c = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Sale [product_id=" + product_id + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", total=" + total + "]";
	}

}
