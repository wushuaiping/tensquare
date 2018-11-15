package io.wooo.tensquare.common.exception;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 12:23 PM
 * @description:
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("请求未获得授权");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
