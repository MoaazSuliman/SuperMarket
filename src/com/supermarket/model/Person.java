package com.supermarket.model;

public class Person {

	private int id;
	private String username;
	private int age;
	private String phone;
	private String password;
	private int role;
	private String shift;

	// there are role = 1 for admin and role = 2 to seller
	public Person() {
	}

	public Person(int id, String username, int age, String phone, String password, int role, String shift) {
		this.id = id;
		this.username = username;
		this.age = age;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.shift = shift;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", age=" + age + ", phone=" + phone + ", password="
				+ password + "shift= " + shift + "]";
	}

}
