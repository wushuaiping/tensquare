package io.wooo.tensquare.recruit.config;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.common.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 9:55 AM
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        log.error(e.getMessage(), e);
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
