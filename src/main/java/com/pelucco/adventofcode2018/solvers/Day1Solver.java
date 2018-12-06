package com.pelucco.adventofcode2018.solvers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Day1Solver extends AbstractSolver {

	Logger log = LoggerFactory.getLogger(Day1Solver.class);
	
	@Value("classpath:day/1/input.txt")
    private Resource inputFile;
	
	private Integer actualFrequency = 0;
	List<Integer> frequencyMap = new LinkedList<Integer>();
	
	@Override
	public void solve() {
		
		List<Integer> frequencyChanges = readFrequenciesAsListOfInteger(inputFile);
		frequencyChanges.stream().forEach(x -> {applyFrequency(x);});
		log.info("first value: {}", actualFrequency);
		
		// reset
		actualFrequency = 0;
		int i = 0; 
		
		while (true) {
			actualFrequency = actualFrequency + frequencyChanges.get(i % frequencyChanges.size());
			if (frequencyMap.contains(actualFrequency)) {
				break;
			}
			i++;
			frequencyMap.add(actualFrequency);
			
		}
		log.info("second value: {}", actualFrequency);
		
	}

	private void applyFrequency(Integer x) {
		actualFrequency = actualFrequency + x;	
	}
	
	

}
