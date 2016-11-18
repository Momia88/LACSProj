package com.hpbu.lacs;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
	private DataParser dataParser = new DataParser();

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

		case "white_color_center":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1x5 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1x5 ORDER BY P_DATE, TIME";
			key = "W_T1x5";
			df = new DecimalFormat("#.####");
			break;
		case "red_color_center":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, R_T1x5 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, R_T1x5 ORDER BY P_DATE, TIME";
			key = "R_T1x5";
			df = new DecimalFormat("#.####");
			break;
		case "green_color_center":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, G_T1x5 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, G_T1x5 ORDER BY P_DATE, TIME";
			key = "G_T1x5";
			df = new DecimalFormat("#.####");
			break;
		case "blue_color_center":
			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, B_T1x5 FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'"
					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, B_T1x5 ORDER BY P_DATE, TIME";
			key = "B_T1x5";
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
//			sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, FLOOR((W_T1YY1 + W_T1YY2 + W_T1YY3 + W_T1YY4 + W_T1YY5 + W_T1YY6 + W_T1YY7 + W_T1YY8 + W_T1YY9)/9) AS W_P9_TOTAL FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
//					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
//					+ "'"
//					+ " GROUP BY P_DATE, TIME, MODEL_NO, PRODUCT_SN, W_T1YY1 , W_T1YY2 , W_T1YY3 , W_T1YY4 , W_T1YY5 , W_T1YY6 , W_T1YY7 , W_T1YY8 , W_T1YY9 ORDER BY P_DATE, TIME";
//			key = "W_P9_TOTAL";
//			df = new DecimalFormat("#");
			break;
		}

		return getChroma(sqlStr, modelType, startTime, endTime, df, key);

	}

	// @POST
	// @Path("/chroma/mysql")
	// @Produces("application/json")
	// public String chromaMysql(@FormParam("modelType") String modelType,
	// @FormParam("startTime") String startTime,
	// @FormParam("endTime") String endTime) {
	//
	// // Mysql
	// String sqlStr = "SELECT * FROM SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO =
	// " + "'" + modelType + "'"
	// + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" +
	// endTime + "'"
	// + " ORDER BY P_DATE, TIME";
	//
	// String chroma = getChromaBrightness(sqlStr, modelType, startTime,
	// endTime);
	// return chroma;
	// }

	// Get Brightness Data from DB
	public String getChroma(String sqlStr, String modelType, String startTime, String endTime, DecimalFormat df,
			String key) {
		// parameter init
		String chroma = null;
		ReportObj reportObj = new ReportObj();
		List<Float> list = new ArrayList<>();
		ArrayList<ChromaObj> chromaList = new ArrayList<ChromaObj>();

		try {

			ResultSet rs = accessManager.getDBData(sqlStr);
			chromaList = dataParser.getChromaList(rs, key);
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
		return "<p> Java Web Service. Hi~Hi~</p>";
	}
}
