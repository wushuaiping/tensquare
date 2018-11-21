package io.wooo.tensquare.article.service;

import io.wooo.tensquare.article.entity.Article;
import io.wooo.tensquare.article.repository.ArticleRepository;
import io.wooo.tensquare.common.exception.BadRequestException;
import lombok.AllArgsConstructor;
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

    public Article getById(String id) {
        Article article = (Article) redisTemplate.opsForValue().get(ARTICLE_KEY + id);
        if (article == null) {
            article = articleRepository.getOne(id);
            redisTemplate.opsForValue().set(ARTICLE_KEY + id, article);
        }
        return article;
    }

    public void save(Article article) {
        redisTemplate.delete(ARTICLE_KEY + article.getId());
        articleRepository.save(article);
    }

    public void deleted(String id) {
        redisTemplate.delete(ARTICLE_KEY + id);
        articleRepository.deleteById(id);
    }

}
