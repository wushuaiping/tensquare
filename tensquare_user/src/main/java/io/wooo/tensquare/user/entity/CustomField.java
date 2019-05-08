package io.wooo.tensquare.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 *  自定义字段
 * @author wushuaiping
 * @date 2019/4/18 16:27
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 自定义字段，用json存储。
     */
    @Column(nullable = false)
    private String customField;

    /**
     * 大业主id
     */
    @Column(nullable = false)
    private Long customerId;
}
