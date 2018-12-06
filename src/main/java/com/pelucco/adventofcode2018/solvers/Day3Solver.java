package com.pelucco.adventofcode2018.solvers;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Day3Solver extends AbstractSolver {

	Logger log = LoggerFactory.getLogger(Day3Solver.class);
	
	@Value("classpath:day/3/input.txt")
    private Resource inputFile;
	
	@Override
	public void solve() {
		
		List<String> claimsStringList = readFrequenciesAsListOfString(inputFile);
		List<Claim> claims = claimsStringList.stream().map(x -> new Claim(x)).collect(Collectors.toList());
		
		// finding claim area
		
		Claim max = claims.stream().max(Comparator.comparing(Claim::getX1)).orElseThrow(NoSuchElementException::new);
		
		log.info("max: {}", max) ;
	}

}
