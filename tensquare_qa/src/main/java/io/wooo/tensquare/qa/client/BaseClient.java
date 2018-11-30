package io.wooo.tensquare.qa.client;

import io.wooo.tensquare.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 3:34 PM
 * @description:
 */
@FeignClient(name = "tensquare-base", path = "/label")
public interface BaseClient {

    @GetMapping("/{id}")
    Result findOne(@PathVariable(value = "id") String id);

}
