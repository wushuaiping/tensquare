package io.wooo.tensquare.user.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.common.util.JwtUtil;
import io.wooo.tensquare.user.entity.User;
import io.wooo.tensquare.user.mapper.UserMapper;
import io.wooo.tensquare.user.model.UserRegisterModel;
import io.wooo.tensquare.user.service.UserService;
import io.wooo.tensquare.user.util.CheckMobileUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 3:24 PM
 * @description:
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    private JwtUtil jwtUtil;

    @PostMapping(value = "/sendsms/{mobile}")
    public Result sendSms(@PathVariable("mobile") String mobile) {
        if (!CheckMobileUtil.isPhoneLegal(mobile)) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "兄dei，你到底能不能填一个正确的手机号？");
        }
        userService.sendSms(mobile);
        return new Result();
    }

    @PostMapping(value = "/register/{code}")
    public Result register(@PathVariable("code") String code,
                           @RequestBody UserRegisterModel user) {
        if (StringUtils.isBlank(code)) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "你验证码都不填，还想注册？脑壳有包？");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "求求你了，设置一个密码行不行？");
        }

        if (StringUtils.isBlank(user.getMobile())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "你别玩了行不行？手机号给我，我才能给你注册账号啊！");
        }

        if (!CheckMobileUtil.isPhoneLegal(user.getMobile())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "兄dei，请你填一个正确的手机号。");
        }
        userService.register(code, UserMapper.MAPPER.registerModelToEntity(user));
        return new Result();
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestParam String mobile, @RequestParam String password) {
        final User user = userService.login(mobile, password);
        if (user == null) {
            return new Result<>(false, HttpStatus.BAD_REQUEST.value(), "请检查用户密码是否正确");
        }

        // 生成令牌
        final String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "user");
        return new Result<>(map);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        userService.delete(id);

        return new Result();
    }
}
