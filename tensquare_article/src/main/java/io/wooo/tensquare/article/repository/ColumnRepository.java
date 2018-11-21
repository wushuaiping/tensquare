package io.wooo.tensquare.article.repository;

import io.wooo.tensquare.article.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 10:48 AM
 * @description:
 */
public interface ColumnRepository extends JpaRepository<Column, String> {
}
