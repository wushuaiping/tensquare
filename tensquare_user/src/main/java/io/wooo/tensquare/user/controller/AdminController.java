package io.wooo.tensquare.user.controller;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.common.util.JwtUtil;
import io.wooo.tensquare.user.entity.Admin;
import io.wooo.tensquare.user.mapper.AdminMapper;
import io.wooo.tensquare.user.model.AdminRegisterModel;
import io.wooo.tensquare.user.service.AdminService;
import io.wooo.tensquare.user.util.CheckMobileUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wushuaiping
 * @date: 2018/11/29 3:19 PM
 * @description:
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminService adminService;

    private JwtUtil jwtUtil;

    @PostMapping
    public Result save(@RequestBody AdminRegisterModel admin) {

        if (StringUtils.isBlank(admin.getUsername())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "兄dei，用户名不能为空。");
        }

        if (StringUtils.isBlank(admin.getPassword())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "兄dei，密码不能为空。");
        }

        if (!CheckMobileUtil.isPhoneLegal(admin.getMobile())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "兄dei，请你填一个正确的手机号。");
        }

        adminService.save(AdminMapper.MAPPER.registerModelToEntity(admin));
        return new Result();
    }

    @GetMapping("/{id}")
    public Result<Admin> getAdminById(@PathVariable("id") String id) {
        return new Result<>(adminService.getAdminById(id));
    }

    @GetMapping
    public Result<PageResult<Admin>> getAdminByPage(@RequestParam String keyword,
                                                    @RequestParam(defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(defaultValue = "20", required = false) Integer size) {
        final Pageable of = PageRequest.of(page, size);
        return new Result<>(adminService.getAdminByPage(keyword, of));
    }

    @PostMapping("/login")
    public Result login(@RequestParam String mobile, @RequestParam String password) {

        final Admin admin = adminService.login(mobile, password);
        if (admin == null) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "请检查用户密码是否正确");
        }

        // 生成令牌
        final String token = jwtUtil.createJWT(admin.getId(), admin.getMobile(), "admin");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "admin");
        return new Result(map);
    }

}
