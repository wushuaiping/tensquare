package io.wooo.tensquare.friend.config.model;

import io.wooo.tensquare.friend.enums.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户登陆之后，解析token中的信息，存放与此
 *
 * @author: wushuaiping
 * @date: 2018/11/30 9:54 AM
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户标识，如果是管理员就是 claims_admin， 如果是用户就是 claims_user
     */
    private IdentityEnum identify;

    /**
     * 用户手机号
     */
    private String mobile;
}
