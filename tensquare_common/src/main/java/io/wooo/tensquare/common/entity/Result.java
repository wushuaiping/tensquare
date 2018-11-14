package io.wooo.tensquare.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 5:05 PM
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
public class Result {

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Object data){
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "success";
        this.data = data;
    }

    public Result(){
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "success";
    }

}
