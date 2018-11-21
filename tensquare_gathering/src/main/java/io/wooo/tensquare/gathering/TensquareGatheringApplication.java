package io.wooo.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TensquareGatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensquareGatheringApplication.class, args);
	}
}
