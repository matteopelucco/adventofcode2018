package com.pelucco.adventofcode2018;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.pelucco.adventofcode2018.solvers.Day1Solver;

@SpringBootApplication
@ComponentScan
public class Adventofcode2018Application implements CommandLineRunner {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(Adventofcode2018Application.class, args);
	}
	
	
	@Autowired
	Day1Solver d1Solver;

	@Override
	public void run(String... args) throws Exception {
		
		d1Solver.solve();
		

	}
}
