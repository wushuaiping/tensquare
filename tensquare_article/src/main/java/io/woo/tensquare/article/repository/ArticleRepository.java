package io.woo.tensquare.article.repository;

import io.woo.tensquare.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 10:48 AM
 * @description:
 */
public interface ArticleRepository extends JpaRepository<Article, String>, CrudRepository<Article, String> {
}
