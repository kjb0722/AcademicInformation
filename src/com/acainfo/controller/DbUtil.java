package com.acainfo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	static public Connection conn() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties prop = new Properties();
			prop.load(new FileReader("database.properties"));
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("[ DB 접속 성공 ]");
			return con;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	static public void disConn(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
