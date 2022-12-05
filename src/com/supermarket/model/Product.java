package com.supermarket.model;

public class Product {

	private int id;
	private String name;
	private int quantity;
	private double price;
	String date;
	String barcode;
	private String categorie_name;

	public Product() {

	}

	public Product(int id, String name, int quantity, double price, String cat_name, String date, String barcode) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.categorie_name = cat_name;
		this.date = date;
		this.barcode = barcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategorie_name() {
		return categorie_name;
	}

	public void setCategorie_id(String categorie_name) {
		this.categorie_name = categorie_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", date=" + date
				+ ", barcode=" + barcode + ", categorie_name=" + categorie_name + "]";
	}

}
