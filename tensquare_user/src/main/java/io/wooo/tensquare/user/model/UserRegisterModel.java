package io.wooo.tensquare.user.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 5:04 PM
 * @description:
 */
@Setter
@Getter
public class UserRegisterModel {

    private String nickname;

    private String password;

    private String mobile;

}