package com.hpbu.lacs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/v1/service")
public class WebService {

	private AccessManager accessManager = new AccessManager();
	private static final String ChromaAPI = "http://10.2.1.38/log/api/CreateSiteState";

	@POST
	@Path("/chroma")
	@Produces("application/json")
	public String chroma(@FormParam("modelType") String modelType, @FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime) {
		String chroma = null;
		ReportObj reportObj = new ReportObj();
		ArrayList<ChromaObj> chromaList = new ArrayList<ChromaObj>();
		List<String> dateList = new ArrayList<>();
		List<String> timeList = new ArrayList<>();
		List<String> productNoList = new ArrayList<>();
		List<Integer> lumenList = new ArrayList<>();

		try {
			// Oracle
			String sqlStr = "SELECT P_DATE, TIME, MODEL_NO, PRODUCT_SN, ANSI_LUMEN FROM SFCS.SFCS_RUNCARD_CHROMA_VIEW WHERE MODEL_NO = "
					+ "'" + modelType + "'" + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'" + endTime
					+ "'" + " ORDER BY P_DATE, TIME";

			// //Mysql
			// String sqlStr = "SELECT * FROM SFCS_RUNCARD_CHROMA_VIEW WHERE
			// MODEL_NO = " + "'" + modelType + "'"
			// + " AND P_DATE BETWEEN " + "'" + startTime + "'" + " AND " + "'"
			// + endTime + "'"
			// + " ORDER BY P_DATE, TIME";

			chromaList = accessManager.getDBData(sqlStr);

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

			LinkedHashMap<Integer, Integer> lumenGroup = Util.getChartData(lumenList);
			List<Integer> barLabels = new ArrayList<>();
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

	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p> Java Web Service. Hi~Hi~</p>";
	}

	@GET
	@Path("/hour")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnHour() {
		Client client = ClientBuilder.newClient();
		String jsonStr = client.target(ChromaAPI + "?queryStartDateTime=20160107140000&queryEndDateTime=20160107150000")
				.request(MediaType.APPLICATION_JSON).header("ApiKey", "uaEPLpCWk4Jmp9nxg83YG0pW0B780wqcU0O1aXPVI04=")
				.header("ApiName", "CreateSiteState").get(String.class);

		return jsonStr;
	}

	@GET
	@Path("/duration")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDuration() {
		Client client = ClientBuilder.newClient();
		String startTime = "20160810000000";
		String endTime = "20160810240000";

		String jsonStr = client
				.target("http://10.2.1.38/log/api/CreateSiteState?queryStartDateTime=" + startTime
						+ "&queryEndDateTime=" + endTime)
				.request(MediaType.APPLICATION_JSON).header("ApiKey", "uaEPLpCWk4Jmp9nxg83YG0pW0B780wqcU0O1aXPVI04=")
				.header("ApiName", "CreateSiteState").get(String.class);

		return jsonStr;
	}
}
