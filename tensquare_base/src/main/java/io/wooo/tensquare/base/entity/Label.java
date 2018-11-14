package io.wooo.tensquare.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 7:44 PM
 * @description:
 */
@Entity
@Table(name = "tb_label")
@Setter
@Getter
public class Label {

    @Id
    private String id;

    /**
     * 标签名称
     */
    private String labelname;

    /**
     * 状态
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 关注数
     */
    private Long fans;

    /**
     * 是否推荐
     */
    private String recommend;

}
