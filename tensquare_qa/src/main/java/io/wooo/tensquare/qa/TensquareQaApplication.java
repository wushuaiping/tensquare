package io.wooo.tensquare.qa;

import io.wooo.tensquare.common.util.IdWorker;
import io.wooo.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"io.wooo.tensquare.qa.client"})
public class TensquareQaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareQaApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return JwtUtil.getInstance();
    }

    @Bean
    public IdWorker idWorker() {
        return IdWorker.getInstance();
    }

}
