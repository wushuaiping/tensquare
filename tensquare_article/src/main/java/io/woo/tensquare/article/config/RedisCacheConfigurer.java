package io.woo.tensquare.article.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Created by Steven on 2018/5/2.
 * <p>
 * RedisCacheConfigurer for Spring Boot 2.x Spring data redis 2.x
 * <p>
 * 主要是 cacheManager 的配置
 */
@Configuration
public class RedisCacheConfigurer extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManagerOfTwoMinutes(RedisConnectionFactory connectionFactory) {
        return buildCacheManager(connectionFactory, 2);
    }

    private RedisCacheManager buildCacheManager(RedisConnectionFactory connectionFactory, int minutes) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .disableCachingNullValues()
                        .entryTtl(Duration.ofMinutes(minutes))).build();
    }
}
