package com.pelucco.adventofcode2018.solvers;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsolidatedTrack {

	Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
	Map<Integer, Integer> maxMinutes = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> getMaxMinutes() {
		return maxMinutes;
		
	}

	private String id;
	
	public ConsolidatedTrack(String id) {
		this.setId(id);
		for (int i = 0; i < 60; i++) {
			m.put(i, 0);
		}
	}

	public void add(Track t) {
		Map<Integer, String> tMap = t.getMap();
		for (Integer minute : tMap.keySet()) {
			String v = tMap.get(minute);
			m.put(minute, v == Track.ASLEEP ? m.get(minute) + 1 : m.get(minute));
		}
		
		int max = 0; 
		for (int i = 0; i < 60; i++) {
			if (m.get(i) >= max) {
				max = m.get(i);
				maxMinutes.put(i, m.get(i));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
