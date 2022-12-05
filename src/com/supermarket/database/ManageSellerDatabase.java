package com.supermarket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.supermarket.model.Method;
import com.supermarket.model.Person;
import com.supermarket.model.Sale;
import com.supermarket.model.SellerSale;

public class ManageSellerDatabase extends DatabaseConnection {
	private Connection connection;
	private PreparedStatement s;
	private String sql;
	private ResultSet result;

	// insert new seller with ID

	public void insertSeller(int id, String username, int age, String phone, String password) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO person values(?,?,?,?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.setString(2, username);
		s.setInt(3, age);
		s.setString(4, phone);
		s.setString(5, password);
		s.setInt(6, 2);// insert 2 in role because he is seller not user #admin#
		s.execute();
		closeConnection(connection, s);

	}

	// insert new seller without ID

	public void insertSeller(String username, int age, String phone, String password, String shift)
			throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO person (username , age , phone , password , role,shift) values(?,?,?,?,?,?)";
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		s.setInt(2, age);
		s.setString(3, phone);
		s.setString(4, password);
		s.setInt(5, 2);
		s.setString(6, shift);
		s.execute();
		closeConnection(connection, s);
	}

	// update selected Seller

	public void updateSeller(int id, String username, int age, String phone, String password,String shift) throws SQLException {
		connection = createConnection();
		sql = "UPDATE person SET username=?, age=? , phone=? ,password=? , shift=? WHERE id=? ";
		s = connection.prepareStatement(sql);
		s.setString(1, username);
		s.setInt(2, age);
		s.setString(3, phone);
		s.setString(4, password);
		s.setString(5, shift);
		s.setInt(6, id);
		s.execute();
		closeConnection(connection, s);

	}

	// delete seller with id
	public void deleteSeller(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE from  person WHERE id=?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		closeConnection(connection, s);
	}

//	return all Sellers
	public ArrayList<Person> getSellers() throws SQLException {
		connection = createConnection();
		sql = "SELECT * from person WHERE role = '2' ";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		ArrayList<Person> arr = new ArrayList<Person>();

		while (result.next()) {

			Person seller = new Person(result.getInt("id"), result.getString("username"), result.getInt("age"),
					result.getString("phone"), result.getString("password"), result.getInt(6), result.getString(7));

			arr.add(seller);
		}
		closeConnectionWithResult(connection, s, result);

		return arr;
	}

	/*
	 * Sale : name price quantity total
	 */
	public void setSalesForSeller(int sellerId, double allTotal) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO salesforseller (seller_id , date , money) VALUES (?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, sellerId);
		s.setString(2, new Method().returnDate());
		s.setDouble(3, allTotal);
		s.execute();
	}

	public ArrayList<SellerSale> getAllSalesForSeller(int id) throws SQLException {

		connection = createConnection();
		sql = "SELECT * FROM salesforseller WHERE seller_id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		return getDefaultSalesForSellers();
	}

	public ArrayList<SellerSale> getAllSalesForSeller(int id, String date) throws SQLException {
		connection = createConnection();
		date += "%";
		sql = "SELECT * FROM salesforseller WHERE seller_id =? AND date like '" + date + "'";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);

		return getDefaultSalesForSellers();
	}

	private ArrayList<SellerSale> getDefaultSalesForSellers() throws SQLException {
		ArrayList<SellerSale> salesForSeller = new ArrayList<>();
		result = s.executeQuery();
		while (result.next()) {
			salesForSeller
					.add(new SellerSale(result.getInt(1), result.getInt(2), result.getString(3), result.getDouble(4)));
		}
		return salesForSeller;
	}

}
