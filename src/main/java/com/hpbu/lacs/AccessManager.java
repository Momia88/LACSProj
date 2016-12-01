package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AccessManager {
	public ResultSet getDBData(String sqlStr) throws Exception {
		OracleDBConnect dbConnect = new OracleDBConnect();
		Connection con = dbConnect.getConn();
		PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
}
