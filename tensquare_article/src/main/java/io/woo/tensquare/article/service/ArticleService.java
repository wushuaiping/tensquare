package io.woo.tensquare.article.service;

import io.woo.tensquare.article.entity.Article;
import io.woo.tensquare.article.repository.ArticleRepository;
import io.wooo.tensquare.common.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "articel", key = "'article_' + #id", condition = "#id != null")
    public Article getById(String id) {
        return articleRepository.getOne(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

}
