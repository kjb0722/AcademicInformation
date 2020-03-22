package com.acainfo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    static public Connection conn() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "scott";
            String password = "tiger";
            con = DriverManager.getConnection(url,user,password);
            System.out.println("[ DB 접속 성공 ]");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    static public void disConn(Connection con){
        try {
            if(con!=null) con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
