package io.woo.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableCaching
public class TensquareArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TensquareArticleApplication.class, args);
    }
}
