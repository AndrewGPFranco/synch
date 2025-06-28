package com.drew.synch;

import org.springframework.boot.SpringApplication;

public class TestSynchApplication {

	public static void main(String[] args) {
		SpringApplication.from(SynchApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
