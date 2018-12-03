package io.wooo.tensquare.friend.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.friend.config.model.LoginUser;
import io.wooo.tensquare.friend.enums.IdentityEnum;
import io.wooo.tensquare.friend.service.FriendService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:09 PM
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
@AllArgsConstructor
public class FriendController {

    private FriendService friendService;

    private HttpServletRequest request;

    @PostMapping("/like/{likeId}")
    public Result likeOrDontLike(@PathVariable(value = "likeId") String likeId) {
        final LoginUser loginUser = (LoginUser) request.getAttribute(IdentityEnum.CLAIMS_USER.getDes());
        if (loginUser == null) {
            return new Result(false, HttpStatus.UNAUTHORIZED.value(), "invalid_token");
        }
        friendService.likeOrDontLike(loginUser.getId(), likeId);
        return new Result();
    }

    @PostMapping("/like/{friendid}/{type}")
    public Result likeByType(@PathVariable("friendid") String friendid,
                             @PathVariable("type") String type) {

        final LoginUser loginUser = (LoginUser) request.getAttribute(IdentityEnum.CLAIMS_USER.getDes());
        if (loginUser == null) {
            return new Result(false, HttpStatus.UNAUTHORIZED.value(), "invalid_token");
        }

        if (StringUtils.equals(type, "0")) {
            // 喜欢
            friendService.like(friendid, loginUser);
        } else {
            // 不喜欢
            friendService.dontLike(friendid, loginUser);
        }

        return new Result();
    }

    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable("friendid") String friendid){
        final LoginUser loginUser = (LoginUser) request.getAttribute(IdentityEnum.CLAIMS_USER.getDes());
        if (loginUser == null){
            return new Result(false, HttpStatus.UNAUTHORIZED.value(), "invalid_token");
        }
        friendService.deleteFriend(friendid, loginUser);
        return new Result();
    }
}
