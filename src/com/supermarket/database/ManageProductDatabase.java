package com.supermarket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.supermarket.model.Method;
import com.supermarket.model.Product;

public class ManageProductDatabase extends DatabaseConnection {

	private Connection connection;
	private PreparedStatement s;
	private String sql;
	private ResultSet result;

	// insert new product with ID

	public void insertProduct(int id, String name, int quantity, double price, String categorie, String barcode)
			throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO product values(?,?,?,?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.setString(2, name);
		s.setInt(3, quantity);
		s.setDouble(4, price);
		s.setString(5, categorie);
		s.execute();
		closeConnection(connection, s);
	}

	// insert new product without ID # id is AUTO INCEREMENT in DB #

	public void insertProduct(String name, int quantity, double price, String categorie, String barcode)
			throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO product(name ,quantity, price ,categorie, date ,barcode) values(?,?,?,?,?,?)";
		s = connection.prepareStatement(sql);
		s.setString(1, name);
		s.setInt(2, quantity);
		s.setDouble(3, price);
		s.setString(4, categorie);

		String date = new Method().returnDate();
		s.setString(5, date);
		s.setString(6, barcode);
		s.execute();
		closeConnection(connection, s);
	}

	// update selected product

	public void updateProduct(int id, String name, int quantity, double price, String categorie, String barcode)
			throws SQLException {
		connection = createConnection();
		sql = "UPDATE product SET name=?, quantity=? ,price=? ,categorie=? , barcode =? WHERE id=? ";
		s = connection.prepareStatement(sql);
		s.setString(1, name);
		s.setInt(2, quantity);
		s.setDouble(3, price);
		s.setString(4, categorie);
		s.setString(5, barcode);
		s.setInt(6, id);
		s.execute();
		closeConnection(connection, s);
	}

	public void deleteProduct(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE from  product WHERE id=?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		closeConnection(connection, s);
	}

	// return all products

	public ArrayList<Product> getProducts() throws SQLException {
		connection = createConnection();
		sql = "SELECT * FROM product";
		return getDefaultProducts();
	}

	public ArrayList<Product> getProductsAfterSearch(String condition) throws SQLException {
		connection = createConnection();
		condition = condition + "%";
		sql = "SELECT * FROM product WHERE name like '" + condition + "'";
		return getDefaultProducts();
	}

	public ArrayList<Product> getProductsAfterSearchByBarcode(String barcode) throws SQLException {
		connection = createConnection();
		barcode = barcode + "%";
		sql = "SELECT * FROM product WHERE barcode like '" + barcode + "'";
		return getDefaultProducts();
	}

	private ArrayList<Product> getDefaultProducts() throws SQLException {
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		ArrayList<Product> arr = new ArrayList<Product>();
		while (result.next()) {
			arr.add(new Product(result.getInt("id"), result.getString("name"), result.getInt("quantity"),
					result.getDouble("price"), result.getString("categorie"), result.getString("date"),
					result.getString("barcode")));
		}
		closeConnectionWithResult(connection, s, result);
		return arr;
	}

	/*************************************************************************************************************************************************************************/
	// get quantity for product with id
	public int getQuantity(int id) throws SQLException {
		connection = createConnection();
		int quantity = 0;
		sql = "select quantity from product WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		result = s.executeQuery();
		if (result.next())
			quantity = result.getInt("quantity");
		closeConnectionWithResult(connection, s, result);
		return quantity;
	}

	// update quantity when some one buy from product
	public void updateQuantity(int id, double newQuantity) throws SQLException {
		connection = createConnection();
		sql = "UPDATE product SET quantity=? WHERE id =?";// insert new quantity after delete taken quantity.
		s = connection.prepareStatement(sql);

		s.setInt(1, (int) newQuantity);
		s.setInt(2, id);
		s.execute();
		closeConnection(connection, s);
	}

	public Product getProductWithBarcode(String barcode) throws SQLException {
		Product product = null;
		connection = createConnection();
		sql = "SELECT * FROM product WHERE barcode =?";
		s = connection.prepareStatement(sql);
		s.setString(1, barcode);
		result = s.executeQuery();
		if (result.next())
			product = new Product(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4),
					result.getString(5), result.getString(6), result.getString(7));
		return product;

	}

	public Product getProductById(int id) throws SQLException {
		Product product = null;
		connection = createConnection();
		sql = "SELECT * FROM product WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		result = s.executeQuery();
		if (result.next())
			product = new Product(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4),
					result.getString(5), result.getString(6), result.getString(7));
		return product;
	}

	private Product getproductBybarcode(String barcode) throws SQLException {
		Product product = null;
		connection = createConnection();
		sql = "SELECT * FROM product WHERE barcode =?";
		s = connection.prepareStatement(sql);
		s.setString(1, barcode);
		result = s.executeQuery();
		if (result.next()) {
			product = new Product(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4),
					result.getString(5), result.getString(6), result.getString(7));
		}
		return product;
	}

	public void addQuantityForProductByBarcode(String barcode, int quantity) throws SQLException {
		Product product = getproductBybarcode(barcode);
		connection = createConnection();
		sql = "UPDATE product SET quantity =? WHERE id =?";
		s = connection.prepareStatement(sql);
		int lastQuantity = product.getQuantity() + quantity;
		s.setInt(1, lastQuantity);
		s.setInt(2, product.getId());
		s.execute();
	}

}
