package com.pelucco.adventofcode2018.solvers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Track {

	private static final String AWAKEN = ".";
	public static final String ASLEEP = "#";
	
	private String id;
	private LocalDateTime start;
	private int lastMinute;
	private String lastAction = AWAKEN;
	private Map<Integer, String> map = new LinkedHashMap<Integer, String>();
	public Map<Integer, String> getMap() {
		return map;
	}

	int asleep = 0; 
	int awaken = 0;

	public Track(String id, LocalDateTime start) {
		this.id = id;
		this.start = normalize(start);
		this.lastMinute = 0;
	}

	private LocalDateTime normalize(LocalDateTime dt) {
		if (dt.getHour() == 23) {
			dt = dt.plusDays(1).withHour(0).withMinute(0);
		}
		return dt;
	}

	public void fallsAt(LocalDateTime t) {
		int m = t.getMinute();
		for (int i = this.lastMinute; i < m; i++) {
			map.put(i, AWAKEN);
		}
		this.lastMinute = m;
		this.lastAction = ASLEEP;
	}

	public void wakesUpAt(LocalDateTime t) {
		int m = t.getMinute();
		for (int i = this.lastMinute; i < m; i++) {
			map.put(i, ASLEEP);
		}
		this.lastMinute = m;
		this.lastAction = AWAKEN;
	}

	@Override
	public String toString() {
		String s = StringUtils.leftPad(id, 6) + ") ";
		for (int i = 0; i < 60; i++) {
			s = s + map.get(i) + " ";
		}
		return s;
		
		
	}

	public void finalizeTrack() {
		for (int i = lastMinute; i < 60; i++) {
			map.put(i, lastAction);
		}
		
		for (String s : map.values()) {
			if (s == ASLEEP) {
				asleep++;
			} else {
				awaken++;
			}
		}
		
	}

	public int getAsleep() {
		return asleep;
	}

	public void setAsleep(int asleep) {
		this.asleep = asleep;
	}

	public int getAwaken() {
		return awaken;
	}

	public void setAwaken(int awaken) {
		this.awaken = awaken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAsleepAt(int i) {
		return map.get(i).equals(ASLEEP);
	}

}
