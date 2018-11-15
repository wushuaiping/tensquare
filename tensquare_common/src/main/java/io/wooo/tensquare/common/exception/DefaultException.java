package io.wooo.tensquare.common.exception;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 12:48 PM
 * @description:
 */
public class DefaultException extends RuntimeException {

    public DefaultException() {
        super("未知错误");
    }

    public DefaultException(String message) {
        super(message);
    }
}
