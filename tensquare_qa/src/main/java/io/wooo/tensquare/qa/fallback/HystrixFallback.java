package io.wooo.tensquare.qa.fallback;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.qa.client.BaseClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author: wushuaiping
 * @date: 2018/12/4 11:23 AM
 * @description:
 */
@Component
public class HystrixFallback implements BaseClient {

    @Override
    public Result findOne(String id) {
        return new Result(false, HttpStatus.SERVICE_UNAVAILABLE.value(), "服务异常，稍后再试");
    }
}
