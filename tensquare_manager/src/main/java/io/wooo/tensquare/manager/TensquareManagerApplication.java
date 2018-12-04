package io.wooo.tensquare.manager;

import io.wooo.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class TensquareManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareManagerApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return JwtUtil.getInstance();
    }
}
