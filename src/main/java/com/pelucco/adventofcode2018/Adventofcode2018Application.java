package com.pelucco.adventofcode2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class Adventofcode2018Application implements CommandLineRunner {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(Adventofcode2018Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = "/day/1/input.txt";

		File file = new ClassPathResource(fileName).getFile();

		Scanner sc = new Scanner(file);
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		String[] arr = lines.toArray(new String[0]);
		for (String s : arr) {
			System.out.println(s);
		}

	}
}
