package com.hpbu.lacs;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Util {

	public static LinkedHashMap<String, Integer> getGroupData(List<Float> list ,DecimalFormat df) {
		LinkedHashMap<String, Integer> chartMap = new LinkedHashMap<>();
		float maxValue = Collections.max(list);
		float minValue = Collections.min(list);
		float increase = (maxValue - minValue) / 10;
		if (increase <= 0) {
			increase = 50;
		}
		int[] count = new int[11];

		try {

			for (float value : list) {
				// value > baseline
				int key = (int) ((value - minValue) / increase);
				count[key]++;
			}
			chartMap.put(df.format(minValue - increase), 0);
			for (int i = 0; i < 11; i++) {
				float value = minValue + increase * i;
				chartMap.put(df.format(value), count[i]);
			}
			chartMap.put(df.format(minValue + increase * 11), 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return chartMap;
	}

	public static float getSTD(List<Float> data) {
		float sum = 0;
		float vars = 0;

		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}
		float ave = ((float) sum) / data.size();
		for (int i = 0; i < data.size(); i++) {
			vars += ((data.get(i) - ave) * (data.get(i) - ave));
		}
		float std = (float) Math.sqrt(vars / data.size());
		return (float) std;
	}
	
	public static float getAvg(List<Float> data) {
		float sum = 0;
		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}
		float ave = ((float) sum) / data.size();
		return (float) ave;
	}

}
