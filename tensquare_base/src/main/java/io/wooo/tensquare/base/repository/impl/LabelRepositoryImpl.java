package io.wooo.tensquare.base.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.wooo.tensquare.base.entity.Label;
import io.wooo.tensquare.base.entity.QLabel;
import io.wooo.tensquare.base.repository.LabelRepositoryCustom;
import io.wooo.tensquare.common.entity.PageResult;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 10:39 AM
 * @description:
 */
@AllArgsConstructor
public class LabelRepositoryImpl implements LabelRepositoryCustom {

    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Label> findBySearch(BooleanBuilder builder) {
        QLabel label = QLabel.label;
        return jpaQueryFactory.selectFrom(label).where(builder).fetch();
    }
}
