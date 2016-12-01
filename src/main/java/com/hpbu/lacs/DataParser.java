package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hpbu.lacs.model.ChromaObj;
import com.hpbu.lacs.model.SiteObj;

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
				chromaObj.setValue(rs.getString(key));
				list.add(chromaObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<SiteObj> getSiteInfo(ResultSet rs) throws SQLException {
		ArrayList<SiteObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				SiteObj siteObj = new SiteObj();
				siteObj.setWSN(rs.getString("WSN"));
				list.add(siteObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
