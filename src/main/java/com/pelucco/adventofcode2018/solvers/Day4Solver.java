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
		
		Track maxAsleep = tracks.stream().max(Comparator.comparing(Track::getAsleep)).orElseThrow(NoSuchElementException::new);
		
		log.info("max : {}", maxAsleep.getId());
		
		List<Track> selectedIdTracks = tracks.stream().filter(x -> x.getId().equals(maxAsleep.getId())).collect(Collectors.toList());
		
		log.info(h);
		selectedIdTracks.stream().forEach(x -> log.info("{}", x));
		
		Map<Integer, Integer> asleepMap = new LinkedHashMap<Integer, Integer>();
		
		for (int i = 0; i < 60; i++) {
			asleepMap.put(i, 0);
			for (int k = 0; k < selectedIdTracks.size(); k++) {
				Track t = selectedIdTracks.get(k); 
				if (t.isAsleepAt(i)) {
					asleepMap.put(i, asleepMap.get(i) + 1);
				}
			}
		}
		
		log.info("{}", asleepMap);
		
		Map<Integer, Integer> sortedAsleepMap = asleepMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		

		log.info("{}", sortedAsleepMap);
		
		log.info("result: {}", (Integer.parseInt(maxAsleep.getId()) * (Integer) sortedAsleepMap.keySet().toArray()[sortedAsleepMap.keySet().size() - 1]));
	}
}
