package com.models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;

	/**
	 * This Class holds the connection to the database (my sql)
	 */
	
	public static Connection getActiveConnection() {
		/*String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		System.out.println(host);*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.9.18.2:3306/firstapp?"
						+ "user=adminzl2YC8Z&password=AQDlRu9zgE7v&characterEncoding=utf8");
			//connection = DriverManager
			
				//.getConnection("jdbc:mysql://localhost:3306/sw-2","root","");
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
