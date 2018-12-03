package io.wooo.tensquare.friend.enums;

/**
 * @author: wushuaiping
 * @date: 2018/12/3 2:00 PM
 * @description:
 */
public enum LikeEnum {

    LIKE("0"),

    RECIPROCAL_LIKING("1"),
    ;
    private String desc;

    LikeEnum() {
    }

    LikeEnum(String desc) {
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
