package io.wooo.tensquare.recruit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 2:01 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_recruit")
public class Recruit {

    @Id
    private String id;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 经验要求
     */
    private String condition;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 任职方式
     */
    private String type;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 企业id
     */
    private String eid;

    /**
     * 状态
     */
    private String state;

    /**
     * 创建日期
     */
    private LocalDateTime createdDate;

    /**
     * 网址
     */
    private String url;

    /**
     * 标签
     */
    private String label;

    /**
     * 职位描述
     */
    private String jobDesc;

    /**
     * 职位要求
     */
    private String jobRequirements;
}
