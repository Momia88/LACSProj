package com.hpbu.lacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<SiteObj> getSiteInfo(ResultSet rs) throws SQLException {
		ArrayList<SiteObj> list = new ArrayList<>();

		try {
			while (rs.next()) {
				SiteObj siteObj = new SiteObj();
				siteObj.setWSN(rs.getString("WSN"));
				siteObj.setSITE_STATE_KEY(rs.getString("SITE_STATE_KEY"));
				siteObj.setSITE_RESULT(rs.getString("SITE_RESULT"));
				siteObj.setRECORD_TIME(rs.getString("RECORD_TIME"));
				list.add(siteObj);
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
				CommStateObj commStateObj = new CommStateObj();
				commStateObj.setCommKey(rs.getString("COMMAND_KEY"));
				commStateObj.setCommResult(rs.getString("COMMAND_RESULT"));
				commStateObj.setCommStateKey(rs.getString("COMMAND_STATE_KEY"));
				commStateObj.setSiteStateKey(rs.getString("SITE_STATE_KEY"));
				list.add(commStateObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getCommResult(ResultSet rs) throws SQLException {
		String str = "";
		try {
			while (rs.next()) {
				str += rs.getString("VALUE") + ",";
			}
			if (str.length() > 0) {
				str = str.substring(0, str.length() - 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
}
