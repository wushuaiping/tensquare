package io.wooo.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TensquareArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareArticleApplication.class, args);
    }
}
