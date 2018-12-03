package io.wooo.tensquare.friend;

import io.wooo.tensquare.common.util.IdWorker;
import io.wooo.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class TensquareFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareFriendApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return IdWorker.getInstance();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return JwtUtil.getInstance();
    }
}
