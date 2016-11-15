package com.hpbu.lacs;

import java.util.List;

public class ReportObj {

	List<String> xLabels;
	List<Integer> yValues;
	String maxValue;
	String minValue;
	String avgValue;
	String stdValue;

	public List<String> getxLabels() {
		return xLabels;
	}

	public void setxLabels(List<String> xLabels) {
		this.xLabels = xLabels;
	}

	public List<Integer> getyValues() {
		return yValues;
	}

	public void setyValues(List<Integer> yValues) {
		this.yValues = yValues;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(String avgValue) {
		this.avgValue = avgValue;
	}

	public String getStdValue() {
		return stdValue;
	}

	public void setStdValue(String stdValue) {
		this.stdValue = stdValue;
	}

}
