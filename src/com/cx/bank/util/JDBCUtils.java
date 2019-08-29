package com.cx.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCUtils {
 public static Connection dbOpen() throws SQLException {
			
		// url����jdbc���ӵ����ݿ�˿ںż�·��
		String url = "jdbc:mysql://127.0.0.1:3306/bank";
		// username�������ݿ���û���,ͬ��passwordΪ����
		String username = "root";
		String password = "root";
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection (url,username,password);
			//System.out.println("连接成功");
			return conn;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 return null;
 }
 public static void dbClose(Connection conn,PreparedStatement stmt) {
	 if(conn!=null) {
		 try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 if(stmt!=null) {
		 try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }

 public static void dbClose(Connection conn,Statement stmt,ResultSet rs) {
	 if(conn!=null) {
		 try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 if(stmt!=null) {
		 try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
}
