package io.wooo.tensquare.base.repository;

import io.wooo.tensquare.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 7:56 PM
 * @description:
 */
public interface LabelRepository extends LabelRepositoryCustom, JpaRepository<Label, String>, QuerydslPredicateExecutor<Label>{
}
