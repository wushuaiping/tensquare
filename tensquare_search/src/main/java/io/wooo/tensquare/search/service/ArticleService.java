package io.wooo.tensquare.search.service;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.search.entity.Article;
import io.wooo.tensquare.search.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 1:55 PM
 * @description:
 */
@Service
@AllArgsConstructor
@Slf4j
public class ArticleService {

    private ArticleRepository articleRepository;

    public PageResult<Article> getArticleByPage(String keyword, Pageable pageable) {
        log.info("search: " + keyword + ", page: " + pageable.getPageSize() + ", size: " + pageable.getOffset());
        final Page<Article> articlePage = articleRepository.findByTitleOrContentLike(keyword, keyword, pageable);
        return new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent());
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return StreamSupport.stream(articleRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }

    public void deleted(String id) {
        articleRepository.deleteById(id);
    }

    public void updateState(String id, String state) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            article.setState(state);
        }
    }
}
