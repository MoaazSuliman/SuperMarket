package com.supermarket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.supermarket.database.DatabaseConnection2;
import com.supermarket.database.ManageProductDatabase;

public class Main {

	public static void main(String[] args) {
		try {

			DatabaseConnection2 db = new DatabaseConnection2();
			Connection connection = db.createConnection();
			db.createConnection();
			String sql = "create table kilosale (id INTEGER PRIMARY KEY AUTOINCREMENT , name text )";
			PreparedStatement s = connection.prepareStatement(sql);
			s.execute();
			System.out.println("table created ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
