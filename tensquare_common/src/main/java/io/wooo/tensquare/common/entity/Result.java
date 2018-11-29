package io.wooo.tensquare.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 5:05 PM
 * @description:
 */
@Setter
@Getter
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 487089606714189177L;
    private boolean flag;

    private Integer code;

    private String message;

    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
    private T data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.flag = true;
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public Result() {
        this.flag = true;
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
    }

}
