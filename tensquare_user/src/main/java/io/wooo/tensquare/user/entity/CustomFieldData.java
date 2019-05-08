package io.wooo.tensquare.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author wushuaiping
 * @date 2019/4/18 16:44
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomFieldData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data;

    private Long customerId;

}
