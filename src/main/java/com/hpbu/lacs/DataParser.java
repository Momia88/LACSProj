package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataParser {

	public ArrayList<ChromaObj> getBrightnessList(ResultSet rs) throws SQLException {
		ArrayList<ChromaObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				ChromaObj chromaObj = new ChromaObj();
				chromaObj.setP_DATE(rs.getString("P_DATE"));
				chromaObj.setTIME(rs.getString("TIME"));
				chromaObj.setMODEL_NO(rs.getString("MODEL_NO"));
				chromaObj.setPRODUCT_SN(rs.getString("PRODUCT_SN"));
				chromaObj.setANSI_LUMEN(rs.getString("ANSI_LUMEN"));
				list.add(chromaObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ChromaObj> getUvList(ResultSet rs) throws SQLException {
		ArrayList<ChromaObj> list = new ArrayList<>();
		try {
			while (rs.next()) {
				ChromaObj chromaObj = new ChromaObj();
				chromaObj.setP_DATE(rs.getString("P_DATE"));
				chromaObj.setTIME(rs.getString("TIME"));
				chromaObj.setMODEL_NO(rs.getString("MODEL_NO"));
				chromaObj.setPRODUCT_SN(rs.getString("PRODUCT_SN"));
				chromaObj.setW_Color_Uniformity(rs.getString("W_Color_Uniformity"));
				list.add(chromaObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
