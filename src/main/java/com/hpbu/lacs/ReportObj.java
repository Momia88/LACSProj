package com.hpbu.lacs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ReportObj {

	List<String> dateList;
	List<String> timeList;
	List<String> productNoList;
	List<Integer> lumenList;
	List<Integer> barLabels;
	List<Integer> barValues;

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

	public List<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}

	public List<String> getProductNoList() {
		return productNoList;
	}

	public void setProductNoList(List<String> productNoList) {
		this.productNoList = productNoList;
	}

	public List<Integer> getLumenList() {
		return lumenList;
	}

	public void setLumenList(List<Integer> lumenList) {
		this.lumenList = lumenList;
	}

	public List<Integer> getBarLabels() {
		return barLabels;
	}

	public void setBarLabels(List<Integer> barLabels) {
		this.barLabels = barLabels;
	}

	public List<Integer> getBarValues() {
		return barValues;
	}

	public void setBarValues(List<Integer> barValues) {
		this.barValues = barValues;
	}

}
