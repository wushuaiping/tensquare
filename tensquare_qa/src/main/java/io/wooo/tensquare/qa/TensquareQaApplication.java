package io.wooo.tensquare.qa;

import io.wooo.tensquare.common.util.IdWorker;
import io.wooo.tensquare.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
