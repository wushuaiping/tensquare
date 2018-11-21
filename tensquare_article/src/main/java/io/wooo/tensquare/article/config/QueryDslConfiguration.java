package io.wooo.tensquare.article.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 *  装配JPAQueryFactory
 * @author: wushuaiping
 * @date: 2018/11/14 10:34 AM
 * @description:
 */
@Configuration
public class QueryDslConfiguration {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
