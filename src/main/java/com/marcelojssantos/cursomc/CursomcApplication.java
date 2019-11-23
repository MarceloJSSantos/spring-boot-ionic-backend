package com.marcelojssantos.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// método herdado de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {

	}
}
