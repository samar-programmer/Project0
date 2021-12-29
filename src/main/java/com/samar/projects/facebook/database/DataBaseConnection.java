package com.samar.projects.facebook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DA_USERNAME = "system";
	private static final String DA_PASSWORD = "sasi";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		/*
		 * Connection connection = null; try { Class.forName(DB_DRIVER); connection =
		 * DriverManager.getConnection(DB_URL, DA_USERNAME, DA_PASSWORD); } catch
		 * (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		 */
		
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, DA_USERNAME, DA_PASSWORD);

		return connection;

	}

}
