package io.wooo.tensquare.common.exception;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 12:18 PM
 * @description:
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("请求资源未找到");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
