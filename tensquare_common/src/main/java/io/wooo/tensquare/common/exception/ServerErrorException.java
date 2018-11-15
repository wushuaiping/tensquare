package io.wooo.tensquare.common.exception;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 12:22 PM
 * @description:
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException(){
        super("服务器异常");
    }

    public ServerErrorException(String message){
        super(message);
    }
}
