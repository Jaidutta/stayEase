package com.learntocode.projects.stayEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StayEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayEaseApplication.class, args);
	}

}
