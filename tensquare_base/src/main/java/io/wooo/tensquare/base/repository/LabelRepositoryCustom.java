package io.wooo.tensquare.base.repository;

import com.querydsl.core.BooleanBuilder;
import io.wooo.tensquare.base.entity.Label;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 10:51 AM
 * @description:
 */
public interface LabelRepositoryCustom {

    List<Label> findBySearch(BooleanBuilder builder);
}
