package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
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
//			Statement stmt = conn.createStatement();
//			String sqlStr = "SELECT P_DATE, TIME, PRODUCT_SN, ANSI_LUMEN FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW where MODEL_NO = 'DHD850' and P_DATE >= '2016-01-01'";
//			ResultSet rset = stmt.executeQuery(sqlStr);
//
//			while (rset.next())
//				System.out.println(rset.getString("P_DATE") + " " + rset.getString("TIME") + " " + rset.getString("PRODUCT_SN") + " " + rset.getString("ANSI_LUMEN"));
//			
//			String sqlStr2 = "SELECT count(*) FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW where MODEL_NO = 'DHD850' and P_DATE >= '2016-01-01'";
//			rset = stmt.executeQuery(sqlStr2);
//			while (rset.next())
//				System.out.println(rset.getString(1));

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
