package io.wooo.tensquare.search.repository;

import io.wooo.tensquare.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 1:52 PM
 * @description:
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
