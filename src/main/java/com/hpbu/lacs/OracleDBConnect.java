package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBConnect {
	/* Operation Server */
	String DbSID = "KSSFCS01";
	String HostName = "KSSFC03-1.coretronic.com";
	String username = "SFCS_RD";
	String userpwd = "RDSFCS";

	// REMOTE ORACLE 8i THIN
	String url = "jdbc:oracle:thin:@" + HostName + ":1521:" + DbSID;
	Connection conn = null;

	public Connection getConn() {
		try {

			// Oracle 8i not pooling connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 取得連線
			conn = DriverManager.getConnection(url, username, userpwd);
			// System.out.println("DataBase connected : " + conDB.toString());
			System.out.println("DataBase connected  ");
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver didn't be load : " + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DataBase didn't connected : " + sqle.toString());
		} finally {
			if (conn == null) {
				try {
					conn.close(); // 關閉資料庫連結
				} catch (SQLException sqle) {
				}
			}
		}

		return conn;
	}
}
