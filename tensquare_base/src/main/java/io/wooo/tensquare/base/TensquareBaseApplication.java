package io.wooo.tensquare.base;

import io.wooo.tensquare.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class TensquareBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareBaseApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return IdWorker.getInstance();
    }
}
