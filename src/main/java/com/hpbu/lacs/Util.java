package com.hpbu.lacs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.stream.events.StartDocument;

public class Util {

	public static LinkedHashMap<Float, Integer> getIntGroupData(List<Integer> list) {
		LinkedHashMap<Float, Integer> chartMap = new LinkedHashMap<>();
		int maxValue = Collections.max(list);
		int minValue = Collections.min(list);
		int increase = (maxValue - minValue) / 10;
		if (increase <= 0) {
			increase = 50;
		}
		int[] count = new int[11];

		try {

			for (int value : list) {
				// value > baseline
				int key = (value - minValue) / increase;
				count[key]++;
			}
			chartMap.put((float) (minValue - increase), 0);
			for (int i = 0; i < 11; i++) {
				int title = minValue + increase * i;
				chartMap.put((float) title, count[i]);
			}
			chartMap.put((float) (minValue + increase * 11), 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return chartMap;
	}

	public static LinkedHashMap<Float, Integer> getFloatGroupData(List<Float> list) {
		LinkedHashMap<Float, Integer> chartMap = new LinkedHashMap<>();
		float USL = (float) 0.016;
		float maxValue = Collections.max(list);
		float minValue = Collections.min(list);
		float increase = (float) ((maxValue - minValue) / 10);
		if (increase <= 0) {
			increase = 50;
		}
		int[] count = new int[11];

		try {

			for (Float value : list) {
				// value > baseline
				int key = (int) ((value - minValue) / increase);
				count[key]++;
			}
			chartMap.put(minValue - increase, 0);
			for (int i = 0; i < 11; i++) {
				float title = minValue + increase * i;
				chartMap.put(title, count[i]);
			}
			chartMap.put(minValue + increase * 11, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return chartMap;
	}
}
