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

	public static LinkedHashMap<Integer, Integer> getChartData(List<Integer> lumen) {
		LinkedHashMap<Integer, Integer> chartMap = new LinkedHashMap<>();
		int maxValue = Collections.max(lumen);
		int minValue = Collections.min(lumen);
		int increase = (maxValue - minValue) / 10;
		if(increase <= 0){
			increase = 50;
		}
		int[] count = new int[11];

		try {

			for (int value : lumen) {
				// value > baseline
				int	key = (value - minValue) / increase;
				count[key]++;
			}
			chartMap.put(minValue - increase, 0);
			for (int i = 0; i < 11; i++) {
				int title = minValue + increase * i;
				chartMap.put(title, count[i]);
			}
			chartMap.put(minValue + increase * 11, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return chartMap;
	}
}
