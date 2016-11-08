package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataParser {

	public ArrayList<ChromaObj> getCourses(Connection con, String sqlStr) throws SQLException {

		ArrayList<ChromaObj> courseList = new ArrayList<ChromaObj>();
		PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		try {
			while (rs.next()) {
				ChromaObj courseObj = new ChromaObj();
				courseObj.setP_DATE(rs.getString("P_DATE"));
				courseObj.setTIME(rs.getString("TIME"));
				courseObj.setMODEL_NO(rs.getString("MODEL_NO"));
				courseObj.setPRODUCT_SN(rs.getString("PRODUCT_SN"));
				courseObj.setANSI_LUMEN(rs.getString("ANSI_LUMEN"));
				courseList.add(courseObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

}
