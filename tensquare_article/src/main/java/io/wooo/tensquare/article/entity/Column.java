package io.wooo.tensquare.article.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 专栏
 *
 * @author: wushuaiping
 * @date: 2018/11/15 10:04 AM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_column")
public class Column implements Serializable {

    private static final long serialVersionUID = 2907493880155722346L;
    @Id
    private String id;

    /**
     * 专栏名称
     */
    private String name;

    /**
     * 专栏简介
     */
    private String summary;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 申请日期
     */
    private LocalDateTime createtime;

    /**
     * 审核日期
     */
    private LocalDateTime checktime;

    /**
     * 状态
     */
    private String state;
}
