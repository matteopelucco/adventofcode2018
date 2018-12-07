package com.pelucco.adventofcode2018.solvers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public abstract class AbstractSolver implements Solver {

	Logger log = LoggerFactory.getLogger(AbstractSolver.class);

	protected List<Integer> readFrequenciesAsListOfInteger(Resource inputFile) {
		List<String> lines = readFrequenciesAsListOfString(inputFile);

		String[] arr = lines.toArray(new String[0]);
		Stream<String> stream = Arrays.stream(arr);
		List<Integer> intList = stream.map(Integer::valueOf).collect(Collectors.toList());

		// just for debug purposes
		intList.stream().forEach(x -> log.info("{}", x));

		return intList;
	}

	protected List<String> readFrequenciesAsListOfString(Resource inputFile) {
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
			if (sc != null)
				sc.close();
		}

		return Collections.EMPTY_LIST;
	}
	
}
