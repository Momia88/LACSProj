package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccessManager {
	public ResultSet getDBData(String sqlStr) throws Exception {
		OracleDBConnect dbConnect = new OracleDBConnect();
		Connection conn = dbConnect.getConn();
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(sqlStr);
		return rs;
	}
}
