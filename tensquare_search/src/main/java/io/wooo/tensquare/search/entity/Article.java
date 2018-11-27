package io.wooo.tensquare.search.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章
 *
 * @author: wushuaiping
 * @date: 2018/11/27 11:42 AM
 * @description:
 */
@Document(indexName = "tensquare_article", type = "article")
@Setter
@Getter
public class Article implements Serializable {
    private static final long serialVersionUID = 2090839377284220063L;

    @Id
    private String id;

    /**
     * 标题
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 正文
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 审核状态
     */
    private String state;
}
