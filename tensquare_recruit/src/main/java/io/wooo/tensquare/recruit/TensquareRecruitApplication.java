package io.wooo.tensquare.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TensquareRecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareRecruitApplication.class, args);
    }
}
