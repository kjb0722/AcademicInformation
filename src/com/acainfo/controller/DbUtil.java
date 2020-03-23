package com.acainfo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	static public Connection conn() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties prop = new Properties();
			prop.load(new FileReader("DB.properties"));
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

	static public void dbClose(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	static public void dbClose(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	static public void dbClose(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	static public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
