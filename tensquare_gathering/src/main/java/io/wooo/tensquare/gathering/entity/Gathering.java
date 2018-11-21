package io.wooo.tensquare.gathering.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/20 3:40 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_gathering")

public class Gathering implements Serializable {

    private static final long serialVersionUID = -946526712931737856L;

    @Id
    private String id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动简介
     */
    private String summary;

    /**
     * 详细说明
     */
    private String detail;

    /**
     * 主办方
     */
    private String sponsor;

    /**
     * 活动图片
     */
    private String image;

    /**
     * 开始时间
     */
    private LocalDateTime starttime;

    /**
     * 截止时间
     */
    private LocalDateTime endtime;

    /**
     * 举办地点
     */
    private String address;

    /**
     * 报名截止时间
     */
    private LocalDateTime enrolltime;

    /**
     * 是否可见
     */
    private String state;

    /**
     * 城市
     */
    private String city;
}