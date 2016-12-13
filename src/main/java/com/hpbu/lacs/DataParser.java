package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.hpbu.lacs.model.ChromaObj;
import com.hpbu.lacs.model.CommStateObj;
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

	public ArrayList<SiteObj> getSiteInfo(ResultSet rs) {
		ArrayList<SiteObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				SiteObj siteObj = new SiteObj();
				siteObj.setWSN(rs.getString("WSN"));
				siteObj.setSITE_STATE_KEY(rs.getString("SITE_STATE_KEY"));
				siteObj.setSITE_RESULT(rs.getString("SITE_RESULT"));
				siteObj.setSITE_KEY(rs.getString("SITE_KEY"));
				siteObj.setRECORD_TIME(rs.getString("RECORD_TIME"));
				list.add(siteObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public HashMap<String, String> getCommInfo(ResultSet rs) {
		HashMap<String, String> list = new HashMap<>();
		try {
			rs.first();
			while (rs.next()) {
				list.put(rs.getString("COMMAND_KEY"), rs.getString("COMMAND_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public HashMap<String, String> getCommDescription(ResultSet rs) {
		HashMap<String, String> list = new HashMap<>();
		try {
			rs.first();
			while (rs.next()) {
				list.put(rs.getString("COMMAND_NAME"), rs.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<CommStateObj> getCommStateInfo(ResultSet rs) throws SQLException {
		ArrayList<CommStateObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				// Column SITE_STATE_KEY, COMMAND_KEY, COMMAND_STATE_KEY,
				// COMMAND_RESULT, SERIAL_NUM, VALUE, UPDATE_TIME
				CommStateObj commStateObj = new CommStateObj();
				commStateObj.setSiteStateKey(rs.getString("SITE_STATE_KEY"));
				commStateObj.setCommKey(rs.getString("COMMAND_KEY"));
				commStateObj.setCommStateKey(rs.getString("COMMAND_STATE_KEY"));
				commStateObj.setCommResult(rs.getString("COMMAND_RESULT"));
				commStateObj.setSerialNum(rs.getString("SERIAL_NUM"));
				commStateObj.setValue(rs.getString("VALUE"));
				commStateObj.setUpdateTime(rs.getString("UPDATE_TIME"));
				list.add(commStateObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
