package com.pelucco.adventofcode2018.solvers;

import java.awt.Rectangle;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		Map<String, Integer> commonSquaresMap = new LinkedHashMap<String, Integer>();
		// a claim overlaps another one if at least 1 covered coordinate is in common
		
		List<String> overlappingClaims = new LinkedList<String>();
		
		
		for (int i = 0; i < claims.size(); i++) {
			List<String> coordinates = claims.get(i).getCoordinates();
			for (String s : coordinates) {
				if (commonSquaresMap.containsKey(s)) {
					commonSquaresMap.put(s, commonSquaresMap.get(s) + 1);
				} else {
					commonSquaresMap.put(s, 1);
				}
			}
		}
		
		Map<String, Integer> collect = commonSquaresMap.entrySet().stream()
				.filter(x -> x.getValue() > 1)
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
		
		log.info("collect: {}", collect.size());
		
		
		// 2nd part
		Rectangle r1 = new Rectangle(0, 0, 1, 1);
		Rectangle r2 = new Rectangle(0, 1, 1, 1);
		log.info("i: {}", r1.intersects(r2));
		
		
		for (int i = 0; i < claims.size(); i++) {
			Claim claimI = claims.get(i);
			// if (overlappingClaims.contains(claimI.getId())) continue;
			
			for (int k = 0; k < claims.size(); k++) {
				if (i == k) continue;
				Claim claimK = claims.get(k);
				// if (overlappingClaims.contains(claimK.getId())) continue;
				
				// log.info("comparing {} with {}. Remaining {} claims to check (found {} overlapping claims so far..)", claimI.getId(), claimK.getId(), claims.size() - i, overlappingClaims.size());
				if (doOverlap(claimI, claimK)) {
					if (!overlappingClaims.contains(claimI.getId())) overlappingClaims.add(claimI.getId());
					if (!overlappingClaims.contains(claimK.getId())) overlappingClaims.add(claimK.getId());
				}
				
			}
			
		}
		
		List<Claim> notOverlappingClaims = claims.stream().filter(x -> !overlappingClaims.contains(x.getId())).collect(Collectors.toList());
		log.info("notOverlappingClaims: {}, id0 = {}", notOverlappingClaims.size(), notOverlappingClaims.size() > 0 ? notOverlappingClaims.get(0).getId() : "none");
		log.info("overlapping claims: {}", overlappingClaims.size()) ;
	}

	private boolean doOverlap(Claim claimI, Claim claimK) {
		Rectangle intersection = claimK.getRectangle().intersection(claimI.getRectangle());
		return !intersection.isEmpty();
	}

}
