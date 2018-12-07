package com.pelucco.adventofcode2018.solvers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Day2Solver extends AbstractSolver {

	Logger log = LoggerFactory.getLogger(Day2Solver.class);
	
	@Value("classpath:day/2/input.txt")
    private Resource inputFile;
	
	private Map<String, Integer> checksumMap = new LinkedHashMap<String, Integer>();
	
	@Override
	public void solve() {
		
		// initialize map
		checksumMap.put("2", 0);
		checksumMap.put("3", 0);
		
		List<String> boxes = readFrequenciesAsListOfString(inputFile);
		boxes.stream().forEach(x -> checkValues(x, 2));
		boxes.stream().forEach(x -> checkValues(x, 3));
		
		log.info("final result: {}", checksumMap.get("2") * checksumMap.get("3"));
		
		// if alphabetical sorted, the 2 similar boxes should be close, so we just need to compare pos x with pos x+1
		Collections.sort(boxes);
		
		String s1 = "";
		String s2 = ""; 
		
		for (int i = 0; i < boxes.size(); i++) {
			
			if (i < boxes.size() -1) {
				s1 = boxes.get(i);
				s2 = boxes.get(i+1);
				
				log.info("---{}---", i);
				log.info("s1: {}", s1);
				log.info("s2: {}", s2);
				
				if (findDifferentLetters(s1, s2) == 1) {
					break; // assuming only 1 in the set
				}
			}
			
			// here, we have s1, and s2 as the right candidates
		}
		
	}
	
	// assuming s1, s2 of the same length
	private int findDifferentLetters(String s1, String s2) {
		int count = 0; 
		String remainingStr = ""; 
		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (c1 == c2) {
				remainingStr = remainingStr + Character.toString(c1);
				continue;
			}
			count++;
		}
		log.info("remainingStr: {}", remainingStr);
		return count;
	}

	private void checkValues(String x, int occ) {
		
		log.info("x: {}", x);
		
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		
		char[] stringToCharArray = x.toCharArray();
		 
		for (char c : stringToCharArray) {
			
			final String s = Character.toString(c);
			
			if (!map.containsKey(s)) {
				map.put(s, new Integer(1));
			} else {
				map.put(s, map.get(s) + 1);
			}
		}
		
		List<String> filteredKeys = map.keySet().stream().filter(l -> map.get(l) == occ).collect(Collectors.toList());
		log.info("filteredKeys: {}", filteredKeys.toString());
		
		if (filteredKeys.size() > 0) {
			checksumMap.put(""+occ, checksumMap.get(""+occ) + 1);
		}
		
	}

	

}
