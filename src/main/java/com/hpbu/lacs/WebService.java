package com.hpbu.lacs;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/v1/service")
public class WebService {

	private AccessManager accessManager = new AccessManager();
	private DataParser  dataParser = new DataParser();

	@POST
	@Path("/chroma")
	@Produces("application/json")
	public String chroma(@FormParam("modelType") String modelType, @FormParam("chartType") String chartType,
			@FormParam("startTime") String startTime, @FormParam("endTime") String endTime) {
		String sqlStr = "";
		System.out.println(chartType);
		switch (chartType) {
		case "brightness":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, ANSI_LUMEN FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, ANSI_LUMEN ORDER BY P_DATE, TIME";
			return getChromaBrightness(sqlStr, modelType, startTime, endTime);

		case "delta_uv":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_COLOR_UNIFORMITY FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_COLOR_UNIFORMITY ORDER BY P_DATE, TIME";
			System.out.println(sqlStr);

			return getChromaDeltaUV(sqlStr, modelType, startTime, endTime);

		case "cct":
			break;

		case "contrast_ratio":
			break;

		case "white_color":
			sqlStr = "SELECT W_T1x1, W_T1x2, W_T1x3, W_T1x4, W_T1x5, W_T1x6, W_T1x7, W_T1x8, W_T1x9 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY W_T1x1, W_T1x2, W_T1x3, W_T1x4, W_T1x5, W_T1x6, W_T1x7, W_T1x8, W_T1x9 ORDER BY P_DATE, TIME";
			break;
			
		case "red_color":
			break;
			
		case "green_color":
			break;
			
		case "blue_color":
			break;
			
		default:
			break;
		}

		return "";
	}

//	@POST
//	@Path("/chroma/mysql")
//	@Produces("application/json")
//	public String chromaMysql(@FormParam("modelType") String modelType, @FormParam("startTime") String startTime,
//			@FormParam("endTime") String endTime) {
//
//		// Mysql
//		String sqlStr = "SELECT * FROM SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = " + "'" + modelType + "'"
//				+ " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime + "'"
//				+ " ORDER BY P_DATE, TIME";
//
//		String chroma = getChromaBrightness(sqlStr, modelType, startTime, endTime);
//		return chroma;
//	}

	public String getChromaDeltaUV(String sqlStr, String modelType, String startTime, String endTime) {
		// parameter init
		String chroma = null;
		ArrayList<ChromaObj> chromaList = new ArrayList<ChromaObj>();
		ReportObj reportObj = new ReportObj();
		List<String> dateList = new ArrayList<>();
		List<String> timeList = new ArrayList<>();
		List<String> productNoList = new ArrayList<>();
		List<Float> deltaUvList = new ArrayList<>();

		try {
			ResultSet rs = accessManager.getDBData(sqlStr);
			chromaList = dataParser.getUvList(rs);
			
			for (int i = 0; i < chromaList.size(); i++) {
				dateList.add(chromaList.get(i).getP_DATE());
				timeList.add(chromaList.get(i).getTIME());
				productNoList.add(chromaList.get(i).getPRODUCT_SN());
				float uv = Float.valueOf(chromaList.get(i).getW_Color_Uniformity());
				deltaUvList.add(uv);
			}

			reportObj.setDateList(dateList);
			reportObj.setTimeList(timeList);
			reportObj.setProductNoList(productNoList);
			reportObj.setDeltaUvList(deltaUvList);
			
			LinkedHashMap<Float, Integer> lumenGroup = Util.getFloatGroupData(deltaUvList);
			List<Float> barLabels = new ArrayList<>();
			barLabels.addAll(lumenGroup.keySet());
			reportObj.setBarLabels(barLabels);
			List<Integer> barValues = new ArrayList<>();
			barValues.addAll(lumenGroup.values());
			reportObj.setBarValues(barValues);
			
			Gson gson = new Gson();
			chroma = gson.toJson(reportObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chroma;
	}

	// Get Brightness Data from DB
	public String getChromaBrightness(String sqlStr, String modelType, String startTime, String endTime) {
		// parameter init
		String chroma = null;
		ReportObj reportObj = new ReportObj();
		List<String> dateList = new ArrayList<>();
		List<String> timeList = new ArrayList<>();
		List<String> productNoList = new ArrayList<>();
		List<Integer> lumenList = new ArrayList<>();
		ArrayList<ChromaObj> chromaList = new ArrayList<ChromaObj>();

		try {

			ResultSet rs = accessManager.getDBData(sqlStr);
			chromaList = dataParser.getBrightnessList(rs);
			
			for (int i = 0; i < chromaList.size(); i++) {
				dateList.add(chromaList.get(i).getP_DATE());
				timeList.add(chromaList.get(i).getTIME());
				productNoList.add(chromaList.get(i).getPRODUCT_SN());
				float light = Float.valueOf(chromaList.get(i).getANSI_LUMEN());
				lumenList.add((int) light);
			}

			reportObj.setDateList(dateList);
			reportObj.setTimeList(timeList);
			reportObj.setProductNoList(productNoList);
			reportObj.setLumenList(lumenList);

			LinkedHashMap<Float, Integer> lumenGroup = Util.getIntGroupData(lumenList);
			List<Float> barLabels = new ArrayList<>();
			barLabels.addAll(lumenGroup.keySet());
			reportObj.setBarLabels(barLabels);
			List<Integer> barValues = new ArrayList<>();
			barValues.addAll(lumenGroup.values());
			reportObj.setBarValues(barValues);

			Gson gson = new Gson();
			chroma = gson.toJson(reportObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chroma;
	}

	// Test
	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p> Java Web Service. Hi~Hi~</p>";
	}
}
