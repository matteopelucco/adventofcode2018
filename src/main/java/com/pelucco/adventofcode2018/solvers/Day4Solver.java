package com.pelucco.adventofcode2018.solvers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Day4Solver extends AbstractSolver {

	Logger log = LoggerFactory.getLogger(Day4Solver.class);
	
	@Value("classpath:day/4/input.txt")
    private Resource inputFile;
	
	@Override
	public void solve() {
		
		List<String> inputStrings = readFrequenciesAsListOfString(inputFile);
		Collections.sort(inputStrings);
		// check ordering
		inputStrings.stream().forEach(x -> log.info(x));
		
		Track track = null;
		List<Track> tracks = new LinkedList<Track>();
		
		String h = StringUtils.leftPad("", 7);
		for (int i = 0; i < 60; i++) {
			h = h + StringUtils.leftPad("" + i, 2);
		}
		log.info(h);
		
		for (int i = 0; i < inputStrings.size(); i++) {
			String s = inputStrings.get(i);
			LocalDateTime dt = LocalDateTime.parse(StringUtils.substring(s, 1,  17), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

			if (StringUtils.contains(s, "begins")) {
				
				String id = StringUtils.split(StringUtils.split(s, "#")[1], " ")[0];
				
				if (i > 0) {
					track.finalizeTrack();
					log.info("{}", track);
					tracks.add(track);
				}
				
				track = new Track(id, dt);
				
			} else if (StringUtils.contains(s, "falls")) {
				track.fallsAt(dt);
			} else {
				track.wakesUpAt(dt);
			}
			
		}
		
		Map<String, Integer> asleepMap = new LinkedHashMap<String, Integer>();
		
		for (Track t : tracks) {
			if (asleepMap.containsKey(t.getId())) {
				asleepMap.put(t.getId(), asleepMap.get(t.getId()) + t.getAsleep());
			} else {
				asleepMap.put(t.getId(), t.getAwaken());
			}
		}
		
		Map<String, Integer> sortedAsleepMap = asleepMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		String maxAsleepId = (String) sortedAsleepMap.keySet().toArray()[sortedAsleepMap.keySet().size() - 1];
		
		log.info("max : {}", maxAsleepId);
		
		List<Track> selectedIdTracks = tracks.stream().filter(x -> x.getId().equals(maxAsleepId)).collect(Collectors.toList());
		
		log.info(h);
		selectedIdTracks.stream().forEach(x -> log.info("{}", x));
		
		Map<Integer, Integer> asleepDaysMap = new LinkedHashMap<Integer, Integer>();
		
		for (int i = 0; i < 60; i++) {
			asleepDaysMap.put(i, 0);
			for (int k = 0; k < selectedIdTracks.size(); k++) {
				Track t = selectedIdTracks.get(k); 
				if (t.isAsleepAt(i)) {
					asleepDaysMap.put(i, asleepDaysMap.get(i) + 1);
				}
			}
		}
		
		log.info("{}", asleepDaysMap);
		
		Map<Integer, Integer> sortedAsleepDaysMap = asleepDaysMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		

		log.info("{}", sortedAsleepDaysMap);
		
		log.info("result: {}", (Integer.parseInt(maxAsleepId) * (Integer) sortedAsleepDaysMap.keySet().toArray()[sortedAsleepDaysMap.keySet().size() - 1]));
		
		
		
		
		
		//******part 2
		Map<String, ConsolidatedTrack> consolidatedTracks = new LinkedHashMap<String, ConsolidatedTrack>();
		for (Track t : tracks) {
			if (consolidatedTracks.containsKey(t.getId())) {
				ConsolidatedTrack consolidatedTrack = consolidatedTracks.get(t.getId());
				consolidatedTrack.add(t);
				consolidatedTracks.put(t.getId(), consolidatedTrack);
			} else {
				ConsolidatedTrack c = new ConsolidatedTrack(t.getId());
				c.add(t);
				consolidatedTracks.put(t.getId(), c);
			}
		}
		
		
		
		String guardId = "";
		Integer minute = 0;
		Integer maxAmount = 0;
		
		for (int i = 0; i < 60; i++) {
			for (ConsolidatedTrack c : consolidatedTracks.values()) {
				int roundMinuteI = c.getMaxMinutes().get(i) != null ? c.getMaxMinutes().get(i) : 0;
				if (roundMinuteI > maxAmount) {
					maxAmount = roundMinuteI;
					guardId = c.getId();
					minute = i;
				}
			}
		}
		
		
		log.info("result 2: {}", Integer.parseInt(guardId) * minute);
		
		
		
		
		
		
	}
}
