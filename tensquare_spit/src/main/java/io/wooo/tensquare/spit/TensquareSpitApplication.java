package io.wooo.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TensquareSpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareSpitApplication.class, args);
    }
}
