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
public class Day2Solver implements Solver {

	Logger log = LoggerFactory.getLogger(Day2Solver.class);
	
	@Value("classpath:day/2/input.txt")
    private Resource inputFile;
	
	private Map<String, Integer> checksumMap = new LinkedHashMap<String, Integer>();
	
	@Override
	public void solve() {
		
		// initialize map
		checksumMap.put("2", 0);
		checksumMap.put("3", 0);
		
		List<String> frequencyChanges = readFrequencies(inputFile);
		frequencyChanges.stream().forEach(x -> checkValues(x, 2));
		frequencyChanges.stream().forEach(x -> checkValues(x, 3));
		
		log.info("final result: {}", checksumMap.get("2") * checksumMap.get("3"));
		
		
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

	private void check2Values(String x) {
		
	}

	private List<String> readFrequencies(Resource inputFile2) {
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile.getFile());
			List<String> stringList = new ArrayList<String>();
			while (sc.hasNextLine()) {
				stringList.add(sc.nextLine());
			}
			
			// just for debug purposes
			stringList.stream().forEach(x -> log.info("{}", x));
			
			return stringList;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sc != null) sc.close();
		}
		
		
		return Collections.EMPTY_LIST;
	}

}
