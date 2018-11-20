package io.woo.tensquare.article.service;

import io.woo.tensquare.article.entity.Article;
import io.woo.tensquare.article.repository.ArticleRepository;
import io.wooo.tensquare.common.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 10:50 AM
 * @description:
 */
@Service
@AllArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;

    private final static String ARTICLE_KEY = "article_";

    private RedisTemplate redisTemplate;

    @Transactional
    public void approvePass(String id) {
        final Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            throw new BadRequestException("该文章不存在");
        }
        article.setState("1");
        articleRepository.save(article);
    }

    @Transactional
    public void thumbup(String id) {
        final Article article = articleRepository.getOne(id);
        if (article == null) {
            throw new BadRequestException("该文章不存在");
        }
        article.setThumbup(article.getThumbup() + 1);
        articleRepository.save(article);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

//    @Cacheable(value = "article", key = "#id")
    public Article getById(String id) {
        Article article = (Article)redisTemplate.opsForValue().get(ARTICLE_KEY + id);
        if (article == null){
             article = articleRepository.getOne(id);
            redisTemplate.opsForValue().set(ARTICLE_KEY + id, article);
        }
        return article;
    }

    @CachePut(value = "article", key = "#id")
    public void save(Article article) {
        articleRepository.save(article);
    }

    @CacheEvict(value = "article", key = "#id")
    public void deleted(String id) {
        articleRepository.deleteById(id);
    }

}
