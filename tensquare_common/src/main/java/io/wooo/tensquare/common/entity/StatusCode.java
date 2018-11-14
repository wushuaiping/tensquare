package io.wooo.tensquare.common.entity;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 5:10 PM
 * @description:
 */
public class StatusCode {

    public static final int OK = 20000; // 成功
    public static final int ERROR = 20001; // 失败
    public static final int LOGINERROR = 20002; // 用户名或密码错误
    public static final int ACCESSERROR = 20003; // 权限不足
    public static final int REMOTERROR = 20004; // 远程调用失败
    public static final int REPEROR = 20005; // 重复操作


}
