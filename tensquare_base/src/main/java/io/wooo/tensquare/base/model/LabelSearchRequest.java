package io.wooo.tensquare.base.model;

import lombok.Getter;
import lombok.Setter;

/**
 *  标签的条件查询
 * @author: wushuaiping
 * @date: 2018/11/14 10:16 AM
 * @description:
 */
@Setter
@Getter
public class LabelSearchRequest {

    private String id;

    private String labelname;

    private String state;

    private Long count;

    private String recommend;

}
