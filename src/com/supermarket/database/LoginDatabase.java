package com.supermarket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.supermarket.model.Person;

public class LoginDatabase extends DatabaseConnection {

	private static PreparedStatement s;
	private static ResultSet res;

	// 1 ==>admin 2==> role 0==> there are not in database.
	public Person Login(String username, String password) throws SQLException {
		Person person = null;
		Connection connection = createConnection();

		s = connection.prepareStatement("select * from person WHERE username =? and password =?");
		s.setString(1, username);
		s.setString(2, password);
		res = s.executeQuery();
		if (res.next()) {
			person = new Person(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5),
					res.getInt(6), res.getString(7));
		}
		closeConnectionWithResult(connection, s, res);
		return person;

	}

}
