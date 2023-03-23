package com.wapanzi.baristajobsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BaristaJobsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaristaJobsApiApplication.class, args);
	}

}
