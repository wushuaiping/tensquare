package io.wooo.tensquare.user.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: wushuaiping
 * @date: 2018/12/3 9:53 AM
 * @description:
 */
@Setter
@Getter
public class UpdateUserModel {

    private String loginUserId;

    private String targetUserId;

    private Integer fansCount;

    private Integer followCount;

}
