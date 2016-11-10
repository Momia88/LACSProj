package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AccessManager {
	public ResultSet getDBData(String sqlStr) throws Exception {

		DBConnect dbConnect = new DBConnect();
		Connection con = dbConnect.getConn();
		DataParser dataParser = new DataParser();
		ArrayList<ChromaObj> list = new ArrayList<ChromaObj>();
		PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
}
