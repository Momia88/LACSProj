package com.hpbu.lacs;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.hpbu.lacs.model.ChromaObj;
import com.hpbu.lacs.model.CommStateObj;
import com.hpbu.lacs.model.LogResultObj;
import com.hpbu.lacs.model.SiteObj;

@Path("/v1/service")
public class WebService {

	private AccessManager accessManager = new AccessManager();
	private DataParser dataParser = new DataParser();
	private String WSN = "WSN";
	private String SITE_RESULT = "SITE_RESULT";
	private String SITE_STATE_KEY = "SITE_STATE_KEY";
	private String SITE_KEY = "SITE_KEY";
	private String RECORD_TIME = "RECORD_TIME";

	@POST
	@Path("/chroma")
	@Produces("application/json")
	public String chroma(@FormParam("modelType") String modelType, @FormParam("chartType") String chartType,
			@FormParam("startTime") String startTime, @FormParam("endTime") String endTime) {
		String sqlStr = "";
		DecimalFormat df = null;
		String key = "";
		switch (chartType) {
		case "brightness":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, ANSI_LUMEN FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, ANSI_LUMEN ORDER BY P_DATE, TIME";
			df = new DecimalFormat("#");
			key = "ANSI_LUMEN";
			break;
		case "delta_uv":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_COLOR_UNIFORMITY FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_COLOR_UNIFORMITY ORDER BY P_DATE, TIME";
			df = new DecimalFormat("#.####");
			key = "W_Color_Uniformity";
			break;
		case "cct":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1CCT5 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1CCT5 ORDER BY P_DATE, TIME";
			df = new DecimalFormat("#");
			key = "W_T1CCT5";
			break;
		case "contrast_ratio":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, Full_ON_OFF FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, Full_ON_OFF ORDER BY P_DATE, TIME";
			df = new DecimalFormat("#");
			key = "Full_ON_OFF";
			break;
		case "white_color_p9":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, FLOOR((W_T1x1 + W_T1x2 + W_T1x3 + W_T1x4 + W_T1x5 + W_T1x6 + W_T1x7 + W_T1x8 + W_T1x9)/9) AS W_P9_TOTAL FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1x1, W_T1x2, W_T1x3, W_T1x4, W_T1x5, W_T1x6, W_T1x7, W_T1x8, W_T1x9 ORDER BY P_DATE, TIME";
			key = "W_P9_TOTAL";
			df = new DecimalFormat("#");
			break;

		case "white_color_x":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_W_X FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_W_X ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_W_X";
			df = new DecimalFormat("#.####");
			break;
		case "red_color_x":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_R_X FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_R_X ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_R_X";
			df = new DecimalFormat("#.####");
			break;
		case "green_color_x":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_G_X FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_G_X ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_G_X";
			df = new DecimalFormat("#.####");
			break;
		case "blue_color_x":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_B_X FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_B_X ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_B_X";
			df = new DecimalFormat("#.####");
			break;
		case "white_color_y":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_W_Y FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_W_Y ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_W_Y";
			df = new DecimalFormat("#.####");
			break;
		case "red_color_y":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_R_Y FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_R_Y ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_R_Y";
			df = new DecimalFormat("#.####");
			break;
		case "green_color_y":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_G_Y FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_G_Y ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_G_Y";
			df = new DecimalFormat("#.####");
			break;
		case "blue_color_y":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_B_Y FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, CHROMATICITY_P5_B_Y ORDER BY P_DATE, TIME";
			key = "CHROMATICITY_P5_B_Y";
			df = new DecimalFormat("#.####");
			break;
		case "white_color_distribution":
			df = new DecimalFormat("#.####");
			break;
		case "red_color_distribution":
			df = new DecimalFormat("#.####");
			break;
		case "green_color_distribution":
			df = new DecimalFormat("#.####");
			break;
		case "blue_color_distribution":
			df = new DecimalFormat("#.####");
			break;
		default:
			// sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN,
			// FLOOR((W_T1YY1 + W_T1YY2 + W_T1YY3 + W_T1YY4 + W_T1YY5 + W_T1YY6
			// + W_T1YY7 + W_T1YY8 + W_T1YY9)/9) AS W_P9_TOTAL FROM
			// SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
			// + "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" +
			// startTime + "'" + " AND " + "'" + endTime
			// + "'"
			// + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1YY1 ,
			// W_T1YY2 , W_T1YY3 , W_T1YY4 , W_T1YY5 , W_T1YY6 , W_T1YY7 ,
			// W_T1YY8 , W_T1YY9 ORDER BY P_DATE, TIME";
			// key = "W_P9_TOTAL";
			// df = new DecimalFormat("#");
			break;
		}

		return getChromaData(sqlStr, modelType, startTime, endTime, df, key);

	}

	// Get Brightness Data from DB
	public String getChromaData(String sqlStr, String modelType, String startTime, String endTime, DecimalFormat df,
			String key) {
		// parameter init
		String chroma = null;
		ReportObj reportObj = new ReportObj();
		List<Float> list = new ArrayList<>();
		ArrayList<ChromaObj> chromaList = new ArrayList<ChromaObj>();
		try {
			ResultSet rs = accessManager.getDBData(sqlStr);
			chromaList = dataParser.getChromaList(rs, key);
			if (chromaList.isEmpty()) {
				return null;
			}
			for (int i = 0; i < chromaList.size(); i++) {
				float value = Float.valueOf(chromaList.get(i).getValue());
				list.add(Float.valueOf(df.format(value)));
			}
			reportObj = getReportObj(list, df);
			Gson gson = new Gson();
			chroma = gson.toJson(reportObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chroma;
	}

	// Test
	@POST
	@Path("/testerlog")
	@Produces(MediaType.TEXT_HTML)
	public String getTesterLog(@FormParam("modelType") String modelType, @FormParam("siteNum") String siteNum,
			@FormParam("startTime") String startTime, @FormParam("endTime") String endTime) {
		Gson gson = new Gson();
		// Command Key
		HashMap<String, String> commKeyPair = new HashMap<>();
		HashMap<String, String> commDescripPair = new HashMap<>();
		HashMap<String, String> commResultPair = new HashMap<>();
		commKeyPair.clear();
		commDescripPair.clear();
		commResultPair.clear();

		// report obj
		LogResultObj logResultObj = new LogResultObj();
		List<HashMap<String, String>> titleList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> recordList = new ArrayList<HashMap<String, String>>();

		// site list
		List<SiteObj> siteList = new ArrayList<SiteObj>();
		// comm list
		List<CommStateObj> commStateList = new ArrayList<CommStateObj>();
		// DecimalFormat df = new DecimalFormat("#.##");
		// Get command key pair
		String keyStr = "select * from HPT_COMMAND_KEY_DESCRIPTION";
		ResultSet rsKey = null;
		try {
			rsKey = accessManager.getDBData(keyStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		commKeyPair = dataParser.getCommInfo(rsKey);
		commDescripPair = dataParser.getCommDescription(rsKey);

		String str = "select MODEL_TYPE,SITE_STATE_KEY,SITE_RESULT,WSN,SITE_KEY,RECORD_TIME,ROW_NUMBER() "
				+ "Over (Partition By WSN,SITE_KEY,MODEL_TYPE ORDER BY RECORD_TIME DESC) as list from HPT_SITE_WSN_PSN_VIEW WHERE WSN != 'test' AND WSN != 'abcdef' AND RECORD_TIME BETWEEN TO_DATE("
				+ "'" + startTime + "'" + ",'YYYY-MM-DD')" + " AND TO_DATE(" + "'" + endTime + "'" + ",'YYYY-MM-DD')";
		String siteStr = "select * from ( " + str + ") where MODEL_TYPE="+ "'" + modelType + "'" + " and SITE_KEY =" + "'" + siteNum + "'" + " and list =1";
		System.out.println(siteStr);
		try {
			ResultSet rs = accessManager.getDBData(siteStr);
			siteList = dataParser.getSiteInfo(rs);
			System.out.println("site:" + siteList.size());
			String commStr = "";

			for (SiteObj siteObj : siteList) {
				HashMap<String, String> record = new HashMap<>();
				titleList = putTitle(titleList, WSN);
				record.put(WSN, siteObj.getWSN());
				titleList = putTitle(titleList, SITE_STATE_KEY);
				record.put(SITE_STATE_KEY, siteObj.getSITE_STATE_KEY());
				titleList = putTitle(titleList, SITE_RESULT);
				record.put(SITE_RESULT, siteObj.getSITE_RESULT());
				titleList = putTitle(titleList, RECORD_TIME);
				record.put(SITE_KEY, siteObj.getSITE_KEY());
				titleList = putTitle(titleList, SITE_KEY);
				record.put(RECORD_TIME, siteObj.getRECORD_TIME());
				// Get Command key
				commStr = "select * from HPT_COMMAND_STATE_RESULT_VIEW where SITE_STATE_KEY = " + "'"
						+ siteObj.getSITE_STATE_KEY() + "'";
				System.out.println(commStr);
				rs = accessManager.getDBData(commStr);
				commStateList = dataParser.getCommStateInfo(rs);
				for (CommStateObj commState : commStateList) {
					String keyId = commState.getCommKey();
					String keyName = commKeyPair.get(keyId);
					if (Integer.valueOf(commState.getSerialNum()) > 1) {
						keyName = keyName + "_" + commState.getSerialNum();
					}
					record.put(keyName, commState.getValue());
					commResultPair.put(keyName, commState.getCommResult());
					if (keyName != null) {
						titleList = putTitle(titleList, keyName);
					}
				}
				recordList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logResultObj.setTitleList(titleList);
		logResultObj.setRecordList(recordList);
		logResultObj.setCommDescriptionPair(commDescripPair);
		logResultObj.setCommResultPair(commResultPair);
		System.out.println(gson.toJson(logResultObj));
		return gson.toJson(logResultObj);
	}

	private List<HashMap<String, String>> putTitle(List<HashMap<String, String>> titleList, String key) {
		for (int i = 0; i < titleList.size(); i++) {
			if (titleList.get(i) == null || titleList.get(i).get("field").equals(key)) {
				return titleList;
			}
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("field", key);
		map.put("title", key);
		titleList.add(map);
		return titleList;
	}

	private ReportObj getReportObj(List<Float> list, DecimalFormat df) {
		LinkedHashMap<String, Integer> groupHash = Util.getGroupData(list, df);
		ReportObj reportObj = new ReportObj();
		List<String> xLabels = new ArrayList<>();
		xLabels.addAll(groupHash.keySet());
		reportObj.setxLabels(xLabels);
		List<Integer> yValues = new ArrayList<>();
		yValues.addAll(groupHash.values());
		reportObj.setyValues(yValues);
		reportObj.setMaxValue(String.valueOf(Collections.max(list)));
		reportObj.setMinValue(String.valueOf(Collections.min(list)));
		reportObj.setAvgValue(df.format(Util.getAvg(list)));
		reportObj.setStdValue(df.format(Util.getSTD(list)));
		return reportObj;
	}

	// Test
	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		HashMap<String, String> pp = new HashMap<>();
		List<Object> list = new ArrayList<>();
		pp.put("id", "123");
		pp.put("name", "bill");
		pp.put("price", "$12");
		list.add(pp);
		pp.put("id", "333");
		pp.put("name", "marry");
		pp.put("price", "$22");
		list.add(pp);
		Gson gson = new Gson();
		String json = gson.toJson(list);

		return json;
	}

	// @POST
	// @Path("/chroma/mysql")
	// @Produces("application/json")
	// public String chromaMysql(@FormParam("modelType") String modelType,
	// @FormParam("startTime") String startTime,
	// @FormParam("endTime") String endTime) {
	//
	// // Mysql
	// String sqlStr = "SELECT * FROM SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO ="
	// + "'" + modelType + "'"
	// + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" +
	// endTime + "'"
	// + " ORDER BY P_DATE, TIME";
	//
	// String chroma = getMySQLData(sqlStr, modelType, startTime, endTime);
	// return chroma;
	// }
}
