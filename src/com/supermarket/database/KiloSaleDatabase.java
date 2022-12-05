package com.supermarket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.supermarket.model.KiloSale;

public class KiloSaleDatabase extends DatabaseConnection2 {

	private Connection connection;
	private String sql;
	private ResultSet result;
	private PreparedStatement s;
	private ArrayList<KiloSale> arr;

	public void insertKiloSale(KiloSale sale) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO kilosale (name ) VALUES (?)";
		s = connection.prepareStatement(sql);
		s.setString(1, sale.getName());
		s.execute();
		connection.close();
		s.close();
	}

	public void deleteKiloSale(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE FROM kilosale WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		connection.close();
		s.close();
	}

	public void updateKiloSale(KiloSale sale) throws SQLException {
		connection = createConnection();
		sql = "UPDATE kilosale SET name =? WHERE id=?";
		s = connection.prepareStatement(sql);
		s.setString(1, sale.getName());
		s.setInt(2, sale.getId());
		s.execute();
		connection.close();
		s.close();
	}

	public ArrayList<KiloSale> getAllKillSales() throws SQLException {
		connection = createConnection();
		sql = "SELECT * FROM kilosale ";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		arr = new ArrayList<>();
		while (result.next())
			arr.add(new KiloSale(result.getInt(1), result.getString(2)));
		return arr;
	}
}
