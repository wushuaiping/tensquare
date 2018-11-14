package io.wooo.tensquare.base.model.filter;

import com.querydsl.core.BooleanBuilder;
import io.wooo.tensquare.base.entity.QLabel;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 标签查询过滤
 *
 * @author: wushuaiping
 * @date: 2018/11/14 10:55 AM
 * @description:
 */
@Getter
@Builder
public class LabelFilter {

    private String id;

    private String labelnameLike;

    private String state;

    private Long count;

    private String recommend;

    public BooleanBuilder toBooleanBuilder() {
        BooleanBuilder builder = new BooleanBuilder();
        QLabel label = QLabel.label;

        if (StringUtils.isNotBlank(id)) {
            builder.and(label.id.eq(id));
        }

        if (StringUtils.isNotBlank(labelnameLike)) {
            builder.and(label.labelname.like("%" + labelnameLike + "%"));
        }

        if (StringUtils.isNotBlank(state)) {
            builder.and(label.state.eq(state));
        }

        if (StringUtils.isNotBlank(recommend)) {
            builder.and(label.recommend.eq(recommend));
        }

        if (count != null) {
            builder.and(label.count.eq(count));
        }

        return builder;
    }

}
