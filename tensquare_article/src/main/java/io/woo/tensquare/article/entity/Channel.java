package io.woo.tensquare.article.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 频道
 *
 * @author: wushuaiping
 * @date: 2018/11/15 10:03 AM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_channel")
public class Channel implements Serializable {

    private static final long serialVersionUID = 4560545086609522335L;
    @Id
    private String id;

    /**
     * 频道名称
     */
    private String name;

    /**
     * 状态
     */
    private String state;
}
