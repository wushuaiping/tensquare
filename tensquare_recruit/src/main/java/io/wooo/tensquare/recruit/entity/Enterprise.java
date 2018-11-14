package io.wooo.tensquare.recruit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 企业
 *
 * @author: wushuaiping
 * @date: 2018/11/14 3:28 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_enterprise")
public class Enterprise {

    @Id
    private String id;

    private String name;

    private String summary;

    private String address;

    private String labels;

    private String coordinate;

    private String ishot;

    private String logo;

    private Integer jobcount;

    private String url;
}
