package io.woo.tensquare.article.config;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.common.exception.BadRequestException;
import io.wooo.tensquare.common.exception.DefaultException;
import io.wooo.tensquare.common.exception.NotFoundException;
import io.wooo.tensquare.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 9:55 AM
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler({BadRequestException.class, NotFoundException.class, ServerErrorException.class, UnauthorizedException.class, DefaultException.class, Exception.class})
    public ResponseEntity<Result> baseExceptionHandler(Exception exception) {
        log.warn(this.getClass().getName() + ":baseExceptionHandler", exception);
        HttpStatus status;
        if (exception instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof ServerErrorException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (exception instanceof UnauthorizedException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof DefaultException){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Result(false, status.value(), exception.getMessage()), status);
    }
}
