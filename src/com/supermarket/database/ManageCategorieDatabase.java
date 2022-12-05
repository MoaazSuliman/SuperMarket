package com.supermarket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.supermarket.model.Categorie;

public class ManageCategorieDatabase extends DatabaseConnection {

	private Connection connection;
	private PreparedStatement s;
	private String sql;
	private ResultSet result;

	// insert new categorie with ID

	public void insertCategorie(int id, String name, String description) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO categorie values(?,?,?)";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.setString(2, name);
		s.setString(3, description);
		s.execute();
		closeConnection(connection, s);
	}

	// insert new categorie without ID

	public void insertCategorie(String name, String description) throws SQLException {
		connection = createConnection();
		sql = "INSERT INTO categorie(name ,description) values(?,?)";
		s = connection.prepareStatement(sql);
		s.setString(1, name);
		s.setString(2, description);
		s.execute();
		closeConnection(connection, s);
	}

	// update selected categorie
	public void updateCategorie(int id, String name, String description) throws SQLException {
		connection = createConnection();
		sql = "UPDATE categorie SET name=? , description =? WHERE id =?";
		s = connection.prepareStatement(sql);
		s.setString(1, name);
		s.setString(2, description);
		s.setInt(3, id);
		s.execute();
		closeConnection(connection, s);
	}

	// delete selected categorie
	public void deleteCategorie(int id) throws SQLException {
		connection = createConnection();
		sql = "DELETE from  categorie WHERE id=?";
		s = connection.prepareStatement(sql);
		s.setInt(1, id);
		s.execute();
		closeConnection(connection, s);
	}

	// return all categorie
	public ArrayList<Categorie> getCategories() throws SQLException {
		connection = createConnection();
		sql = "SELECT * from categorie";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		ArrayList<Categorie> arr = new ArrayList<Categorie>();
		while (result.next()) {
			arr.add(new Categorie(result.getInt("id"), result.getString("name"), result.getString("description")));
		}
		closeConnectionWithResult(connection, s, result);
		return arr;
	}

}
