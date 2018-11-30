package io.wooo.tensquare.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 9:59 AM
 * @description:
 */

@AllArgsConstructor
@Getter
public enum IdentityEnum {

    CLAIMS_ADMIN("管理员"),

    CLAIMS_USER("用户"),
    ;
    private String des;
}
