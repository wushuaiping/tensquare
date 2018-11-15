package io.wooo.tensquare.common.exception;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 12:20 PM
 * @description:
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(){
        super("请求不正确");
    }

    public BadRequestException(String message){
        super(message);
    }
}
