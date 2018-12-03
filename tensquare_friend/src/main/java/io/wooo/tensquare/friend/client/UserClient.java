package io.wooo.tensquare.friend.client;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.friend.client.model.UpdateUserClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:19 PM
 * @description:
 */
@FeignClient(name = "tensquare-user", path = "/user")
public interface UserClient {

    @PutMapping("/update/count")
    Result updateUserCount(@RequestBody UpdateUserClientModel updateUserClientModel);
}
