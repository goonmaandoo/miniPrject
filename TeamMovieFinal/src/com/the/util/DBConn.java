package com.the.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private DBConn() {}

	private static Connection dbConn = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	public static Connection getInstance() { // 연결
		if (dbConn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1522:xe";
				String id = "c##human";
				String pw = "human";
				dbConn = DriverManager.getConnection(url, id, pw);
				System.out.println("DBConnection....");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dbConn;
	}

	public static void dbClose() { // 닫기
		try {

			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (dbConn != null)
				dbConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			st = null;
			dbConn = null;
		}
	}

	public static int statementUpdate(String sql) {
		DBConn.getInstance();
		int rValue = -1;
		if (dbConn != null) {
			try {
				if (st == null)
					st = dbConn.createStatement();
				rValue = st.executeUpdate(sql); // insert,delete,update
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("not connected...");
		}
		return rValue;
	}

	public static ResultSet statementQuery(String sql) { // select
		DBConn.getInstance();
		if (DBConn.dbConn != null) {
			try {
				if (st == null)
					st = dbConn.createStatement();

				rs = st.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("not connected...");
		}
		return rs;
	}

	
}