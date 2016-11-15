package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataParser {

	public ArrayList<ChromaObj> getChromaList(ResultSet rs, String key) throws SQLException {
		ArrayList<ChromaObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				ChromaObj chromaObj = new ChromaObj();
				chromaObj.setP_DATE(rs.getString("P_DATE"));
				chromaObj.setTIME(rs.getString("TIME"));
				chromaObj.setMODEL_NO(rs.getString("MODEL_NO"));
				chromaObj.setPRODUCT_SN(rs.getString("PRODUCT_SN"));
				chromaObj.setV1(rs.getString(key));
				list.add(chromaObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
