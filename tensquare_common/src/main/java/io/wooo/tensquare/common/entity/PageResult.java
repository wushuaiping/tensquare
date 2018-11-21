package io.wooo.tensquare.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 5:08 PM
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {

    private long total;

    private Object rows;

}
